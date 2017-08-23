package com.neko.androidexamples.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.neko.androidexamples.R;

public class _IntentActivity extends AppCompatActivity {

    private Button explicitButton;
    private Button implicitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        findViewComponents();
        setViewListeners();
    }

    /**
     * Finds the buttons inside the XML - activity_intent.xml
     */
    private void findViewComponents() {
        explicitButton = (Button) findViewById(R.id.btnExplicitIntent);
        implicitButton = (Button) findViewById(R.id.btnImplicitIntent);
    }

    /**
     * Shows 2 forms to call onClick method
     * Setting button OnClick Callback
     */
    private void setViewListeners() {
        // EXPLICIT CALL
        explicitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Executed in an Activity, so 'this' is the Context
                Intent intent = new Intent(_IntentActivity.this, ExplicitActivity.class);

                // Sending some data (we will retrieve from ExplicitActivity
                intent.putExtra("SOME_DATA", getString(R.string.intent_activity_extra_data));
                startActivity(intent);
            }
        });

        // IMPLICIT CALL
        implicitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the text message with a string
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Some Message");
                sendIntent.setType("text/plain");

                // Verify that the intent will resolve to an activity
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendIntent);
                }
            }
        });
    }
}
