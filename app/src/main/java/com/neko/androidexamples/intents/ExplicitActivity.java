package com.neko.androidexamples.intents;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.neko.androidexamples.R;
import com.neko.androidexamples.services.MyService;

public class ExplicitActivity extends AppCompatActivity {

    private Context context = this;
        private static final int REQUEST_PIN = 2381;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        // Getting data from Intent
        if (getIntent().hasExtra("SOME_DATA")) {
            String dataRetrieved = getIntent().getStringExtra("SOME_DATA");
            // showing data retrieved through intent
            ((TextView) findViewById(R.id.tvIntentExtraData)).setText(dataRetrieved);
        }

        setButtonsListeners();
    }

    private void setButtonsListeners() {
        // START SERVICE
        (findViewById(R.id.btnExplicitStartService)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyService.serviceRunning) {
                    ((Button) view).setText(R.string.service_stopped);
                    stopService(new Intent(context, MyService.class));
                } else {
                    ((Button) view).setText(R.string.service_running);
                    startService(new Intent(context, MyService.class));
                }
            }
        });

        (findViewById(R.id.btnExplicitStartActivityForResult)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityForResult.class);
                intent.putExtra("data_1", "data1");
                intent.putExtra("data_2", "data2");
                intent.putExtra("data_3", 100);
                startActivityForResult(intent, REQUEST_PIN);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PIN && resultCode == Activity.RESULT_OK && null != data) {
            if (data.hasExtra(ActivityForResult.DATA_TO_BE_RESULTED)) {
                ((TextView) findViewById(R.id.tvActivityForResultData))
                        .setText(data.getStringExtra(ActivityForResult.DATA_TO_BE_RESULTED));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(context, MyService.class));
    }

    /* -----------------------------------------------------------------
    /  -------------------   BROADCAST RECEIVER   ----------------------
    /  -----------------------------------------------------------------*/

    /**
     * Handles the data when BroadcastIntent is called
     */
    private BroadcastReceiver mUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // Attendances update complete
            if (MyService.ACTION_SERVICE_HAS_FINISHED.equals(action)) {
                String data_from_service = "";
                if (intent.hasExtra(MyService.EXTRA_SERVICE_RESPONSE_DATA)) {
                    data_from_service = intent.getStringExtra(MyService.EXTRA_SERVICE_RESPONSE_DATA);
                    // Setting data retrieved from Serive through Broadcast Receiver
                    ((TextView) findViewById(R.id.tvBroadcastReceiverData)).setText(data_from_service);
                }
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(mUpdateReceiver, getBroadcastIntentFilter());
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(mUpdateReceiver);
    }

    /**
     * Subscribe the activity to ACTION_SERVICE_HAS_FINISHED and handle messages through BroadcastReceivers
     *
     * @return
     */
    private IntentFilter getBroadcastIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyService.ACTION_SERVICE_HAS_FINISHED);
        return intentFilter;
    }
}
