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

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;
import com.gospelministries.gmimedia.R;
import com.gospelministries.gmimedia.bean.Stream;

import static com.gospelministries.gmimedia.util.Constants.TAG_POSITION;

/**
 * Activity that can playback a video stream.
 *
 * @since 0.9_beta
 */
public class VideoPlayerActivity extends ActionBarActivity {

    /**
     * What streampoint to play if none is chosen.
     */
    public final static int DEFAULT_POSITION = 0;

    /**
     * View that plays the video.
     */
    private VideoView videoView;

    /**
     * Stream to play.
     */
    private Stream stream;

    /**
     * Index of streampoint to play.
     */
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        // find view elements
        videoView = (VideoView) findViewById(R.id.video_view);

        // get data sent from parent activity
        Bundle bundle = getIntent().getExtras();
        stream = bundle.getParcelable(Stream.class.getCanonicalName());
        position = bundle.getInt(TAG_POSITION, DEFAULT_POSITION);

        // set the activity's window title
        getSupportActionBar().setTitle(stream.getName());

        // execute StreamVideo AsyncTask
        new StreamVideo().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.video_player, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_full_screen:
                toggleFullScreen();
                return true;

            case R.id.action_mute_audio:
                toggleMute();
                return true;

            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Toggles the full screen.
     */
    public void toggleFullScreen() {
        // TODO: implement
//        Window w = getWindow();
//
//        //Window winHnd = this.getWindow();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        w.setFlags(w.getAttributes().FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //@Override
    public void fullScreen()
    {}

    /**
     * Toggles the audio.
     */
    public void toggleMute() {
        // TODO: implement
//        Window w = getWindow();
//        Window winHnd = this.getWindow();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        w.setFlags(w.getAttributes()., WindowManager.LayoutParams);
    }

    /**
     * Shows a progress dialog while buffering and tries to playback the stream.
     */
    private class StreamVideo extends AsyncTask<Void, Void, Void> {

        /**
         * Dialog showing that the stream is buffering.
         */
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // show a progress dialog
            progressDialog = new ProgressDialog(VideoPlayerActivity.this);

            progressDialog.setTitle(stream.getName());
            progressDialog.setMessage(getString(R.string.buffering));
            //progressDialog.setIndeterminate(false);
            progressDialog.setIndeterminate(true);

            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // check if available
            if (!stream.isAvailable()) {
                showStreamingErrorDialog();
                this.cancel(true);
            }

            // set media controller
            MediaController mediacontroller = new MediaController(VideoPlayerActivity.this);
            mediacontroller.setAnchorView(videoView);
            videoView.setMediaController(mediacontroller);

            // set URL
            Uri streamUri = Uri.parse(stream.getStreampointList().get(position).getUrl());
            videoView.setVideoURI(streamUri);

            // try to play it
            videoView.requestFocus();

            videoView.setOnPreparedListener(new OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    // Close the progress bar and play the video
                    progressDialog.dismiss();
                    videoView.start();
                }
            });
        }

        /**
         * Lets the user know, that something went wrong.
         */
        private void showStreamingErrorDialog() {
            AlertDialog.Builder adb = new AlertDialog.Builder(VideoPlayerActivity.this);

            // set title
            adb.setTitle("Streaming error.");

            // set dialog message
            adb.setMessage("Stream currently offline.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });

            // create alert dialog
            AlertDialog alertDialog = adb.create();

            // show it
            alertDialog.show();
        }
    }
}
