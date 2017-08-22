package com.neko.androidexamples.view_pager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import com.neko.androidexamples.R;
import com.neko.androidexamples.common.fragments.FisrtFragment;
import com.neko.androidexamples.common.fragments.SecondFragment;
import com.neko.androidexamples.common.fragments.ThirdFragment;
import com.neko.androidexamples.common.pager_animations.ZoomOutPageTransformer;

public class ViewPagerActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        // Initializing Pager
        pager = (ViewPager) findViewById(R.id.pager);
        // Initializing Pager Adapter
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        // Adding Fragments to Pager Adapter
        pagerAdapter.addFragment(new FisrtFragment());
        pagerAdapter.addFragment(new SecondFragment());
        pagerAdapter.addFragment(new ThirdFragment());
        // setting adapter to pager
        pager.setAdapter(pagerAdapter);

        // setting swipe animation
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
    }
}
