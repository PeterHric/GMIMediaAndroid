package com.gospelministries.gmimedia;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video_Low_Mission extends Activity {

    private final static int TV_NONE_SELECTED = Integer.MAX_VALUE;

    VideoView videoview;
    // Declare some variables
    private ProgressDialog pDialog;
    private int selectedTV = 0;
    private String selectedTVName;
    private videoResolution selectedResolution = videoResolution.UNSUPPORTED_RES;
    private String selectedStreamUrl; // To be filled later

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the extra messages sent from the parent activity
        Intent intent = getIntent();

        // Get parameters sent by MainActivity
        selectedTV = intent.getIntExtra(MainActivity.CHOSEN_TV, TV_NONE_SELECTED);
        selectedTVName = intent.getStringExtra(MainActivity.CHOSEN_TV_NAME);
        selectedStreamUrl = intent.getStringExtra(MainActivity.CHOSEN_STREAM);
        int ordinal = intent.getIntExtra(MainActivity.CHOSEN_RESOLUTION, videoResolution.UNSUPPORTED_RES.ordinal());
        selectedResolution = videoResolution.values()[ordinal];

        assert (selectedResolution != videoResolution.UNSUPPORTED_RES);

        // Set the layout from video_main.xml
        setContentView(R.layout.activity_video__low__mission);
        // Find your VideoView in your video_main.xml layout
        videoview = (VideoView) findViewById(R.id.VideoView);

        String title = selectedTVName;
        switch (selectedResolution) {
            case LOW_RES:
                title += " Lo";
                break;
            case MEDIUM_RES:
                title += " Med";
                break;
            case HIGH_RES:
                title += " Hi";
                break;
            default:
                assert (false);
        }

        // Set the activity's window title
        this.setTitle(title);

        // Execute StreamVideo AsyncTask
        new StreamVideo().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.video__low__mission, menu);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }

    public void fullScreenClicked() {
        Window w = getWindow();

        //Window winHnd = this.getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        w.setFlags(w.getAttributes().FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void muteClicked() {
        //Window w = getWindow();
        //Window winHnd = this.getWindow();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //w.setFlags(w.getAttributes()., WindowManager.LayoutParams);
    }

    public enum videoResolution {
        LOW_RES,
        MEDIUM_RES,
        HIGH_RES,

        UNSUPPORTED_RES
    }

    // StreamVideo AsyncTask
    private class StreamVideo extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressbar
            pDialog = new ProgressDialog(Video_Low_Mission.this);
            String title = selectedTVName;

            switch (selectedResolution) {
                case LOW_RES:
                    title += " (Low Quality)";
                    break;
                case MEDIUM_RES:
                    title += " (Medium Quality)";
                    break;
                case HIGH_RES:
                    title += " (High Quality)";
                    break;
                default:
                    assert (false);
            }

            // Set progressbar title
            pDialog.setTitle(title);
            // Set progressbar message
            pDialog.setMessage("Buffering...");
            pDialog.setIndeterminate(false);
            // Show progressbar
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            try {
                // Start the MediaController
                MediaController mediacontroller = new MediaController(
                    Video_Low_Mission.this);
                mediacontroller.setAnchorView(videoview);

                // Get the URL from String VideoURL
                Uri video = Uri.parse(selectedStreamUrl);
                videoview.setMediaController(mediacontroller);
                videoview.setVideoURI(video);

                //boolean streamOnLine = mediacontroller.checkInputConnectionProxy(videoview);
                boolean streamOnLine = true;

                if (!streamOnLine) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Video_Low_Mission.this);

                    // set title
                    adb.setTitle("Streaming error.");

                    // set dialog message
                    adb.setMessage("Stream currently off-line.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // TODO: Exit the video stream action
                                //Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            }
                        });

                    // create alert dialog
                    AlertDialog alertDialog = adb.create();

                    // show it
                    alertDialog.show();

                    //cancel async task
                    this.cancel(true);

                    return;
                }

                videoview.requestFocus();
                videoview.setOnPreparedListener(new OnPreparedListener() {
                    // Close the progress bar and play the video
                    public void onPrepared(MediaPlayer mp) {
                        pDialog.dismiss();
                        videoview.start();
                    }
                });
            } catch (Exception e) {
                pDialog.dismiss();

                // Error Here

                Log.e("Video_Low_Mission.onPostExecute() Error: ", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // private class StreamVideo extends AsyncTask<Void, Void, Void> {
}
