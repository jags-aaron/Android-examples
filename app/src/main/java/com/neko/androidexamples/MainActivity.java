package com.neko.androidexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.neko.androidexamples.list_view.ListViewActivity;
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
                // calling listView
                startActivity(ListViewActivity.class);
                break;
            case R.id.btnViewPager:
                // calling ViewPager
                startActivity(ViewPagerActivity.class);
                break;
            case R.id.btnViewPagerTabbed:
                // calling ViewPagerTabbed
                startActivity(ViewPagerTabbedActivity.class);
                break;
        }
    }

    private void startActivity(Class classToBeCalled){
        Intent intent = new Intent(this, classToBeCalled);
        startActivity(intent);
    }
}
