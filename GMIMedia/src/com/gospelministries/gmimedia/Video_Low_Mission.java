package com.gospelministries.gmimedia;

import android.app.ActionBar;
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
import android.view.SurfaceHolder;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.List;

public class Video_Low_Mission extends Activity {

	// Declare some variables
	private ProgressDialog pDialog;
	VideoView videoview;

    private final int TV_NONE_SELECTED = Integer.MAX_VALUE;
    private int selectedTV             = 0;
    private int selectedResolution     = 0;
    private List<String> Quality;
    private String selectedStreamUrl; // To be filled later

    public enum videoResolution
    {
        LOW_RES    ,
        MEDIUM_RES ,
        HIGH_RES   ,

        UNSUPPORTED_RES
    };

    private videoResolution resolution = videoResolution.UNSUPPORTED_RES;

    public void setVideoResolution(videoResolution res) { resolution = res; }

    void fillStreamUrls ()
    {
        // Put in your Video stream URLs here
        selectedStreamUrl = "rtsp://streamer1.streamhost.org:1935/salive/lcit"; // Italy
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // Get the extra messages sent from the parent activity
        Intent intent = getIntent();

        // Get parameters sent by MainActivity
        selectedTV         = intent.getIntExtra(MainActivity.CHOSEN_TV, TV_NONE_SELECTED);
        selectedStreamUrl  = intent.getStringExtra(MainActivity.CHOSEN_STREAM);
        selectedResolution = intent.getIntExtra(MainActivity.CHOSEN_RESOLUTION, videoResolution.UNSUPPORTED_RES.ordinal());

        assert(selectedResolution < videoResolution.UNSUPPORTED_RES.ordinal());
        // Convert to the enum value now
        resolution = videoResolution.values()[selectedResolution];

        //fillStreamUrls();

		// Set the layout from video_main.xml
		setContentView(R.layout.activity_video__low__mission);
		// Find your VideoView in your video_main.xml layout
		videoview = (VideoView) findViewById(R.id.VideoView);
		// Execute StreamVideo AsyncTask
		new StreamVideo().execute();
	}
	

	// StreamVideo AsyncTask
	private class StreamVideo extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressbar
			pDialog = new ProgressDialog(Video_Low_Mission.this);
			// Set progressbar title
			pDialog.setTitle("Mission TV (Low Quality)");
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
                if(!streamOnLine)
                {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Video_Low_Mission.this);
                    // set title
                    adb.setTitle("Streaming error.");

                    // set dialog message
                    adb.setMessage("Stream currently off-line.")
                       .setCancelable(false)
                       .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog,int id) {
                              // ToDo: Exit the video stream action
                              //Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                              Intent i = new Intent(getApplicationContext(), MainActivity.class);
                              startActivity(i);
                          }
                    });

                    // create alert dialog
                    AlertDialog alertDialog = adb.create();

                    // show it
                    alertDialog.show();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.video__low__mission, menu);
		return true;
	}

}