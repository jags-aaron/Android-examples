package com.neko.androidexamples.intent_and_intentfilters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.neko.androidexamples.R;
import com.neko.androidexamples.intent_and_intentfilters.explicit.ExplicitActivity;

public class IntentActivity extends AppCompatActivity {

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
     */
    private void setViewListeners() {
        // Setting onClick callback
        explicitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Executed in an Activity, so 'this' is the Context
                Intent intent = new Intent(IntentActivity.this, ExplicitActivity.class);

                // Sending some data (we will retrieve from ExplicitActivity
                intent.putExtra("SOME_DATA", "Hello..!! I'm any value to be passed through intent");
                startActivity(intent);
            }
        });

        // Setting onClick callback
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
