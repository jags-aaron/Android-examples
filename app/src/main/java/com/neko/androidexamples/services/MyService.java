package com.neko.androidexamples.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public static final String ACTION_SERVICE_HAS_FINISHED = "action_service_has_finished";
    public static final String EXTRA_SERVICE_RESPONSE_DATA = "extra_service_response_data";
    private final boolean IS_STOPPED = true;

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
        // Sending message through BR to ExplicitActivity
        sendBroadcastReceiver();
    }

    private void initHeavyProcessInBackground(){
        Log.d(LOG, "Doing process in background... ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Sends a Broadcast receiver to all the components subscribed to ACTION_SERVICE_HAS_FINISHED
     */
    private void sendBroadcastReceiver(){
        Intent mIntent = new Intent(ACTION_SERVICE_HAS_FINISHED);

        if(serviceRunning){
            mIntent.putExtra(EXTRA_SERVICE_RESPONSE_DATA, "Hello, I'm a Message from MyService");
        }else{
            mIntent.putExtra(EXTRA_SERVICE_RESPONSE_DATA, "GoodBye, I was a Message from MyService");
        }

        sendBroadcast(mIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG, "Service has been stopped...");
        serviceRunning = false;
        // Sending message through BR to ExplicitActivity
        sendBroadcastReceiver();
    }
}
