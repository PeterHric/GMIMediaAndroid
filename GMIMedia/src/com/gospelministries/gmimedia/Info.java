package com.gospelministries.gmimedia;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Info extends Activity {
	final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return true;
	}
	
	public void linkEmailClicked(View v)
    {
        Intent it = new Intent(Intent.ACTION_SEND);
        String[] tos = {getString(R.string.emailAddress)};
        it.putExtra(Intent.EXTRA_EMAIL, tos);
        it.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.emailSubject));
        it.setType("text/plain");
        startActivity(it);
    }
	
	
	public void aboutClicked(View v)
    {
      ///
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
 
			// set title
			alertDialogBuilder.setTitle("About GMI");
 
			// set dialog message
			alertDialogBuilder
				.setMessage("Gospel Ministries International is a faith-based, volunteer-driven organization which seizes opportunities to carry God’s love, through sacrifice, to the world.\n\n -Education                                                                                            -Prison Ministry                                                                                                                                                                          -Healthcare                                                                                                                       - Care for the Abandoned and Needy\n\n These are some of its priorities, while utilizing technology such as aviation and mass media to leverage the impact and influence of its mission.\n\n Seeking to maximize the number of persons involved and areas reached, it creates service opportunities for diverse skills and education. Believing that time is very short, its decision making reflects a sense of urgency. It supports and encourages people and other organizations which share these principles.\n\n GMI has been a completely faith-based organization from the start. David Gates and the GMI board and administrators manage GMI with a financial philosophy built on prayer and faith. No fundraising projects are undertaken, and no funds are explicitly solicited. Through this philosophy of simply making people aware of needs, and reaching out to God in prayer, GMI has grown in leaps and bounds.\n\n Today GMI directs and supports medical aviation programs in Guyana, Venezuela, Bolivia, and Norway, reaching people in the interior with the gospel and emergency medical help. GMI maintains a total of 6 schools in Guyana, Venezuela, Bolivia, and Peru, providing basic education and industrial training to Adventists and non-Adventists alike.\n\n GMI’s largest ministry is through 3 television networks (several more in development). The Advenir Spanish Television Network (ASTN) reaches the entire Spanish speaking world through satellite and cable networks in many areas. The Adevir Portuguese Television Network (APTN) will soon reach the entire Portuguese speaking world, and the Caribbean Family Network (CFN) is rebuilding to reach the Caribbean in Caribbean English and French. A new network broadcasting in Romanian is currently in development. The beautiful Christian family programming provided by these networks includes kids programming, soothing nature scenes, music videos, cooking shows, historical programs, and Biblical preaching. GMI continues striving to fulfill God’s calling, providing help to those in need and sharing the wonderful news of our soon returning Jesus with those who have not yet heard.")
				.setCancelable(false)
				.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// Do Action For Ok Button
					}
				  });
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
    }
	
	
	public void wesbiteClicked(View v)
    {
      ///
		startActivity(new Intent(Intent.ACTION_VIEW, 
			    Uri.parse("http://www.gospelministry.org")));

		
    }

}
