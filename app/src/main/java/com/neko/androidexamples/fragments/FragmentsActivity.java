package com.neko.androidexamples.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.neko.androidexamples.R;
import com.neko.androidexamples.common.fragments.FirstFragment;
import com.neko.androidexamples.common.fragments.SecondFragment;
import com.neko.androidexamples.common.fragments.ThirdFragment;

public class FragmentsActivity extends AppCompatActivity implements FirstFragment.OnFragmentFirstCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        FirstFragment fragment1 = new FirstFragment();
        fragment1.setListener(this); // setting fragment callback
        SecondFragment fragment2 = new SecondFragment();
        ThirdFragment fragment3 = new ThirdFragment();

        // Inflating fragments
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container1, fragment1, "fragment_1").commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container2, fragment2, "ragment_2").commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container3, fragment3, "ragment_3").commit();

        /*
        * beginTransaction().add (add new fragment in stack)
        * beginTransaction().replace (Replace all fragments in stack)
        * */
    }

    /**
     * Method override from OnFragmentFirstCallback interface
     * It is called when the (FRAGMENT 1) button is clicked
     * @see FirstFragment
     */
    @Override
    public void fragmentSayHello() {
        Toast.makeText(FragmentsActivity.this, "Fragment1 say hello", Toast.LENGTH_LONG).show();
    }
}
