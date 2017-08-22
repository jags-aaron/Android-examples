package com.neko.androidexamples.view_pager_with_tabs;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.neko.androidexamples.R;
import com.neko.androidexamples.common.fragments.FisrtFragment;
import com.neko.androidexamples.common.fragments.SecondFragment;
import com.neko.androidexamples.common.fragments.ThirdFragment;
import com.neko.androidexamples.view_pager.PagerAdapter;

/**
 * Class that shows a ViewPager that interacts with TabLayout
 *
 * NOTE: Design Support Library must be implemented in Gradle to user TabLayout
 * @link https://developer.android.com/training/material/design-library.html#CreateFAB
 */
public class ViewPagerTabbedActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_tabbed);

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

        // Finding TabLayout and adding tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Pager and Tab onChangeListeners
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
