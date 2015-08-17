/*
 * Copyright (C) 2015 Gospel Ministries International
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
import com.gospelministries.gmimedia.ui.activity.AbstractStreamListFragment;

/**
 * Fragment that shows the list of other than GMI radio stations.
 *
 * @since 0.9_beta
 */
public class OtherRadioListFragment extends AbstractStreamListFragment {

    @Override
    public int getStreamFileId() {
        return R.raw.radio_streams_other;
    }
}
