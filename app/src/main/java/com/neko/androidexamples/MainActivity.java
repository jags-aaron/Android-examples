package com.neko.androidexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.neko.androidexamples.fragments.FragmentsActivity;
import com.neko.androidexamples.intents._IntentActivity;
import com.neko.androidexamples.list_view.ListViewActivity;
import com.neko.androidexamples.recycler_view.RecyclerViewActivity;
import com.neko.androidexamples.view_pager.ViewPagerActivity;
import com.neko.androidexamples.view_pager_with_tabs.ViewPagerTabbedActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onOptionButtonSelected(View view) {
        switch (view.getId()){
            case R.id.btnListView:
                // LIST VIEW
                startNewActivity(ListViewActivity.class);
                break;
            case R.id.btnViewPager:
                // VIEW PAGER
                startNewActivity(ViewPagerActivity.class);
                break;
            case R.id.btnViewPagerTabbed:
                // VIEW PAGER WITH TABS
                startNewActivity(ViewPagerTabbedActivity.class);
                break;
            case R.id.btnRecyclerView:
                // RECYCLER VIEW
                startNewActivity(RecyclerViewActivity.class);
                break;
            case R.id.btnIntents:
                // RECYCLER VIEW
                startNewActivity(_IntentActivity.class);
                break;
            case R.id.btnFragments:
                // FRAGMENTS
                startNewActivity(FragmentsActivity.class);
                break;
        }
    }

    private void startNewActivity(Class classToBeCalled){
        Intent intent = new Intent(this, classToBeCalled);
        startActivity(intent);
    }
}
