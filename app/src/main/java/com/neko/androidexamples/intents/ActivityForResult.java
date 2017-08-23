package com.neko.androidexamples.intents;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.neko.androidexamples.R;

public class ActivityForResult extends AppCompatActivity {

        public static final String DATA_TO_BE_RESULTED = "data_to_be_resulted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);

        (findViewById(R.id.buttonSendData)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(DATA_TO_BE_RESULTED, getString(R.string.data_activity_for_result));
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
