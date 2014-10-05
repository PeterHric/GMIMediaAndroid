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
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
 * Activity with a list view of streams. Clicking on an item will start the
 * player.
 *
 * @since 0.9_beta
 */
public abstract class AbstractStreamListActivity extends ActionBarActivity {

    /**
     * List of streams.
     */
    private StreamList streamList;

    /**
     * View to choose a stream.
     */
    private ListView listView;

    /**
     * Should return the String that is written in the header.
     *
     * @return String in header
     */
    public abstract String getHeaderTitle();

    /**
     * Should return the ID of the xml resource defining all the streams.
     *
     * @return ID of xml resource
     */
    public abstract int getStreamFileId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_list);

        // TODO: why a header and a title?
        setTitle(getString(R.string.gospel_ministries));

        // find view elements
        listView = (ListView) findViewById(R.id.list_view);

        // add elements to the ListView
        addHeader();

        try {
            addStreams();
        } catch (Exception e) {
            // TODO: what todo here?
            Log.e("AbstractStreamListActivity.addStreams()", e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Class activity;

        switch (item.getItemId()) {
            case R.id.action_radio:
                activity = RadioListActivity.class;
                break;

            case R.id.action_tv:
                activity = TvListActivity.class;
                break;

            case R.id.action_information:
                activity = InfoActivity.class;
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        startActivity(new Intent(getApplicationContext(), activity));
        return true;
    }

    /**
     * Add a header to the list view.
     */
    private void addHeader() {
        View header = getLayoutInflater().inflate(R.layout.item_header, listView, false);
        TextView headerTextView = (TextView) header.findViewById(R.id.header_text_view);
        headerTextView.setText(getHeaderTitle());
        listView.addHeaderView(header);
    }

    /**
     * Add the streams to the list view. Streams will be read from a file.
     *
     * @throws Exception
     */
    private void addStreams() throws Exception {
        streamList = SerializerHelper.deserializeStreamList(this, getStreamFileId());
        StreamArrayAdapter streamArrayAdapter = new StreamArrayAdapter(this, R.layout.item_stream, streamList);
        listView.setAdapter(streamArrayAdapter);

        // Callback
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // clicked on header?
                if (position == 0) return;

                Stream stream = streamList.getStreams().get(position - 1);
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
        new SelectStreampointDialog(this, stream, new DialogInterface.OnClickListener() {
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
        Intent intent = new Intent(getApplicationContext(), VideoPlayerActivity.class);
        intent.putExtra(Stream.class.getCanonicalName(), stream);
        intent.putExtra(TAG_POSITION, position);
        startActivity(intent);
    }
}
