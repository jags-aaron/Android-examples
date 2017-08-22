package com.neko.androidexamples.intent_and_intentfilters.explicit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.neko.androidexamples.R;

public class ExplicitActivity extends AppCompatActivity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        // Getting data from Intent
        if (getIntent().hasExtra("SOME_DATA")) {
            String dataRetrieved = getIntent().getStringExtra("SOME_DATA");
            // showing data retrieved through intent
            Toast.makeText(this, dataRetrieved, Toast.LENGTH_LONG).show();
        }

        setButtonsListeners();
    }

    private void setButtonsListeners() {
        (findViewById(R.id.btnExplicitStartService)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyService.serviceRunning){
                    ((Button) view).setText("Service Stopped.");
                    stopService(new Intent(context, MyService.class));
                }else{
                    ((Button) view).setText("Service Running (see log)...");
                    startService(new Intent(context, MyService.class));
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(context, MyService.class));
    }
}
