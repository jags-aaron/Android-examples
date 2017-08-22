package com.neko.androidexamples.intent_and_intentfilters.explicit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private final String LOG = this.getClass().getSimpleName();
    public static boolean serviceRunning = false;

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG, "Service is running...");
        serviceRunning = true;

        initHeavyProcessInBackground();
    }

    private void initHeavyProcessInBackground(){
        Log.d(LOG, "Doing process in background... ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG, "Service has been stopped...");
        serviceRunning = false;
    }
}
