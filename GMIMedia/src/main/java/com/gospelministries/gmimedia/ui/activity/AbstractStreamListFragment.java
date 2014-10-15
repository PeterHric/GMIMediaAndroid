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

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.gospelministries.gmimedia.R;
import com.gospelministries.gmimedia.adapter.StreamArrayAdapter;
import com.gospelministries.gmimedia.bean.Stream;
import com.gospelministries.gmimedia.bean.StreamList;
import com.gospelministries.gmimedia.ui.dialog.SelectStreampointDialog;
import com.gospelministries.gmimedia.util.SerializerHelper;

import static com.gospelministries.gmimedia.util.Constants.TAG_POSITION;

/**
 * Fragment with a list view of streams. Clicking on an item will start the
 * player.
 *
 * @since 0.9_beta
 */
public abstract class AbstractStreamListFragment extends Fragment {

    /**
     * List of streams.
     */
    private StreamList streamList;

    /**
     * View to choose a stream.
     */
    private ListView listView;

    /**
     * Should return the ID of the xml resource defining all the streams.
     *
     * @return ID of xml resource
     */
    public abstract int getStreamFileId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_stream_list, container, false);

        // find view elements
        listView = (ListView) rootView.findViewById(R.id.list_view);

        // add elements to the ListView
        try {
            addStreams();
        } catch (Exception e) {
            // TODO: what todo here?
            Log.e("AbstractStreamListActivity.addStreams()", e.getMessage());
        }

        return rootView;
    }

    /**
     * Add the streams to the list view. Streams will be read from a file.
     *
     * @throws Exception
     */
    private void addStreams() throws Exception {
        streamList = SerializerHelper.deserializeStreamList(getActivity(), getStreamFileId());
        StreamArrayAdapter streamArrayAdapter = new StreamArrayAdapter(getActivity(), R.layout.item_stream, streamList);
        listView.setAdapter(streamArrayAdapter);

        // Callback
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Stream stream = streamList.getStreams().get(position);
                stream.sortStreampointList();

                if (stream.getStreampointList().size() == 1) {
                    launchStream(stream, 0);
                } else if (stream.getStreampointList().size() > 1) {
                    chooseQualityAndLaunchStream(stream);
                } else {
                    // TODO: a stream with no streampoint! show an error?
                }
            }
        });
    }

    /**
     * Show a dialog to choose a quality and launch that.
     *
     * @param stream the stream
     */
    private void chooseQualityAndLaunchStream(final Stream stream) {
        new SelectStreampointDialog(getActivity(), stream, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                launchStream(stream, position);
            }
        }).show();
    }

    /**
     * Start the player activity with the given stream.
     *
     * @param stream   the stream
     * @param position index of the streampoint
     */
    private void launchStream(final Stream stream, final int position) {
        // TODO: adapt for audio streams here or make a generic player?
        Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
        intent.putExtra(Stream.class.getCanonicalName(), stream);
        intent.putExtra(TAG_POSITION, position);
        startActivity(intent);
    }
}
