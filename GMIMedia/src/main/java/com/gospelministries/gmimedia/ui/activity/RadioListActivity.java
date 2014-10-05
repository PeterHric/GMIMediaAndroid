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

import com.gospelministries.gmimedia.R;

/**
 * Activity that shows the list of radio stations.
 *
 * @since 0.9_beta
 */
public class RadioListActivity extends AbstractStreamListActivity {

    @Override
    public String getHeaderTitle() {
        return getString(R.string.title_activity_radio_list);
    }

    @Override
    public int getStreamFileId() {
        return R.raw.radio_streams;
    }
}
