package com.neko.androidexamples.http_request;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.neko.androidexamples.R;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_request);

        String URL_REQUEST = "https://myurl";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "NAME OF STUDENT");
            jsonObject.put("year", "3rd");
            jsonObject.put("curriculum", "Arts");
            jsonObject.put("birthday", "5/5/1993");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // requesting POST to conekta API
        HttpUtil http = new HttpUtil(URL_REQUEST, jsonObject.toString(), HttpUtil.POST,
                new HttpUtil.OnHttpUtilResponse() {
                    @Override
                    public void onHttpResponse(JSONObject Jobject) {

                    }

                    @Override
                    public void onHttpFail(final JSONObject Jobject, final String error) {
                        HttpRequestActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Log.e("TAG", "Fail cause: " + error);

                                Toast.makeText(HttpRequestActivity.this, "Fail", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

        http.execute();
    }
}
