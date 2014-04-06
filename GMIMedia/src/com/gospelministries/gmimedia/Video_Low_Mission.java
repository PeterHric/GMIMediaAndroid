package com.gospelministries.gmimedia;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
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

public class Video_Low_Mission extends Activity {
	// Put in your Video URL here
	private String VideoURL = "rtsp://streamer1.streamhost.org:1935/salive/GMIalfal";
	// Declare some variables
	private ProgressDialog pDialog;
	VideoView videoview;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
				Uri video = Uri.parse(VideoURL);
				videoview.setMediaController(mediacontroller);
				videoview.setVideoURI(video);

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
				
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}

		}

	}

	// Not using options menu for this tutorial
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.video__low__mission, menu);
		return true;
	}

}