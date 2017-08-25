package com.neko.androidexamples.http_request;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by n3k0 on 8/24/17.
 * It uses okHttp3
 * @link https://github.com/square/okhttp
 */

public class HttpUtil extends AsyncTask<Void, Void, Void> {

    public static final int POST = 0;
    public static final int GET = 1;

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();
    private String url;
    private String json;
    private int type = 0;
    private OnHttpUtilResponse listener;

    public HttpUtil(String url, String json, int type, OnHttpUtilResponse listener) {
        this.url = url;
        this.json = json;
        this.listener = listener;
        this.type = type;
    }

    private void post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .addHeader("Cache-Control", "no-cache")
                .url(url)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response != null) {
            String jsonData = response.body().string();
            JSONObject Jobject = null;
            String error = "";
            String success = "";
            try {
                Jobject = new JSONObject(jsonData);
                if (Jobject.has("error"))
                    error = Jobject.getString("error");
                if(Jobject.has("success"))
                    success = Jobject.getString("success");
                if(Jobject.has("kind"))
                    success = Jobject.getString("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (listener != null) {
                if (response.isSuccessful() && !success.equals("")) {
                    listener.onHttpResponse(Jobject);
                } else {
                    listener.onHttpFail(Jobject, error);
                }
            }
        }
    }

    private void get(String url) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Cache-Control", "no-cache")
                .url(url)
                .get()
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response != null) {
            String jsonData = response.body().string();
            JSONObject Jobject = null;
            String error = "";
            String success = "";
            try {
                Jobject = new JSONObject(jsonData);
                if (Jobject.has("error"))
                    error = Jobject.getString("error");
                if(Jobject.has("success"))
                    success = Jobject.getString("success");
                if(Jobject.has("kind"))
                    success = Jobject.getString("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (listener != null) {
                if (response.isSuccessful() && !success.equals("")) {
                    listener.onHttpResponse(Jobject);
                } else {
                    listener.onHttpFail(Jobject, error);
                }
            }
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            switch (type) {
                case POST:
                    post(url, json);
                    break;
                case GET:
                    get(url);
                    // TODO some get method
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface OnHttpUtilResponse {
        void onHttpResponse(JSONObject Jobject);

        void onHttpFail(JSONObject Jobject, String error);
    }
}
