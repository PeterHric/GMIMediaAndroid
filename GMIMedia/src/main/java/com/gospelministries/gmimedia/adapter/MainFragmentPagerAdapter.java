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

package com.gospelministries.gmimedia.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.gospelministries.gmimedia.R;
import com.gospelministries.gmimedia.ui.activity.RadioListFragment;
import com.gospelministries.gmimedia.ui.activity.TvListFragment;

import java.util.Locale;

/**
 * Contains a list of fragments.
 *
 * @since 1.0.0
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    /**
     * The context of the adapter.
     */
    private Context context;

    /**
     * Creates a new adapter.
     *
     * @param fragmentManager The FragmentManager used to load the fragments.
     * @param context         The context in which the adapter will be used.
     */
    public MainFragmentPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
    }

    /**
     * Returns the number of fragments in the Adapter.
     *
     * @return the number of fragments
     */
    @Override
    public int getCount() {
        return 2;
    }

    /**
     * Creates and returns the fragment at the given position.
     *
     * @param position The index of the fragment.
     *
     * @return the fragment
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TvListFragment();
            case 1:
                return new RadioListFragment();
        }

        throw new IllegalStateException("Position unknown");
    }

    /**
     * Returns the page title of the fragment at the given position.
     *
     * @param position The index of the fragment.
     *
     * @return the page title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        Locale locale = Locale.getDefault();

        switch (position) {
            case 0:
                return context.getString(R.string.tv).toUpperCase(locale);
            case 1:
                return context.getString(R.string.radio).toUpperCase(locale);
        }

        throw new IllegalStateException("Position unknown");
    }
}
