/*
 * Copyright (C) 2014 Gospel Ministries International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.gospelministries.gmimedia.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.gospelministries.gmimedia.R;
import com.gospelministries.gmimedia.adapter.MainFragmentPagerAdapter;

/**
 * Activity with a {@code ViewPager} showing TV and radio streams in two tabs.
 *
 * @since 1.0.0
 */
public class MainActivity extends ActionBarActivity {

    /**
     * Adapter containing the fragments.
     */
    MainFragmentPagerAdapter mainFragmentPagerAdapter;

    /**
     * View pager displaying the fragments.
     */
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), this);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(mainFragmentPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int position, float offset, int offsetPixels) { }

            @Override
            public void onPageScrollStateChanged(int position) { }
        });

        getSupportActionBar().setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);

        for (int i = 0; i < mainFragmentPagerAdapter.getCount(); i++) {
            Tab tab = getSupportActionBar().newTab();
            tab.setText(mainFragmentPagerAdapter
                .getPageTitle(i))
                .setTabListener(new ActionBar.TabListener() {
                    @Override
                    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) { }

                    @Override
                    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) { }
                });

            getSupportActionBar().addTab(tab);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_information:
                startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
