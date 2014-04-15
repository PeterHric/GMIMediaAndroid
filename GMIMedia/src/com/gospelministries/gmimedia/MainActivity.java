package com.gospelministries.gmimedia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity {

	
    private ListView tvChannelListView;
    public final static String CHOSEN_TV = "com.gospelministries.gmimedia.TV";
    public final static String CHOSEN_RESOLUTION = "com.gospelministries.gmimedia.RESOLUTION";

    // Static array of channels
    VideoStream aVideoStreams[];
    // Dynamic list of channels
    List<VideoStream> lVideoStreams;


    // Should be dynamic and fill only active (i.e. currently on-line) streams
    void fillListOfStreamUrls ()
    {
        // ToDo: Some pseudo-code ideas
        //while(!staticList.Empty())
        //        lStreamUrls.add(staticList.element());

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        setTitle("Gospel Ministries");

        aVideoStreams = new VideoStream[]
        {
            new VideoStream(R.drawable.image0, "Mission TV", "rtsp://streamer1.streamhost.org:1935/salive/GMImissiontvl", null, "rtsp://streamer1.streamhost.org:1935/salive/GMImissiontvh"),
            new VideoStream(R.drawable.image1, "HCBN Philippines", "rtsp://streamer1.streamhost.org:1935/salive/GMIhcbnl", null, null),
            new VideoStream(R.drawable.image2, "Global Family Network", null, "rtsp://streamer1.streamhost.org:1935/salive/GMIgfnm", "rtsp://streamer1.streamhost.org:1935/salive/GMIgfnh"),
            new VideoStream(R.drawable.image3, "2CBN", "rtsp://streamer1.streamhost.org:1935/salive/GMI2cbnl", "rtsp://streamer1.streamhost.org:1935/salive/GMI2cbnm", "rtsp://streamer1.streamhost.org:1935/salive/GMI2cbnh"),
            new VideoStream(R.drawable.image4, "Red ADvenir", "rtsp://streamer1.streamhost.org:1935/salive/GMIredadvenirl", "rtsp://streamer1.streamhost.org:1935/salive/GMIredadvenirm", "rtsp://streamer1.streamhost.org:1935/salive/GMIredadvenirh"),
            new VideoStream(R.drawable.image5, "Alfa Television", "rtsp://streamer1.streamhost.org:1935/salive/GMIalfal", "rtsp://streamer1.streamhost.org:1935/salive/GMIalfam", "rtsp://streamer1.streamhost.org:1935/salive/GMIalfah"),
            new VideoStream(R.drawable.image6, "Light Channel Hungary", null, "rtsp://streamer1.streamhost.org:1935/salive/hungarian", null),
            new VideoStream(R.drawable.image7, "Light Channel Rumania", null, "rtsp://streamer1.streamhost.org:1935/salive/romanian", null),
            new VideoStream(R.drawable.image8, "Light Channel Germany", null, "rtsp://streamer1.streamhost.org:1935/salive/lctvde", null),
            new VideoStream(R.drawable.image9, "Terceiro Anjo", "rtsp://streamer1.streamhost.org:1935/salive/GMI3anjol", "rtsp://streamer1.streamhost.org:1935/salive/GMI3anjom", "rtsp://streamer1.streamhost.org:1935/salive/GMI3anjoh"),
            new VideoStream(R.drawable.image10, "HCBN Indonesia", "rtsp://streamer1.streamhost.org:1935/salive/GMIhcbnINlow", "rtsp://streamer1.streamhost.org:1935/salive/GMIhcbnINmed", "rtsp://streamer1.streamhost.org:1935/salive/GMIhcbnINhigh"),
            new VideoStream(R.drawable.image11, "TV Famille","rtsp://streamer1.streamhost.org:1935/salive/GMItvfl", "rtsp://streamer1.streamhost.org:1935/salive/GMItvfm", "rtsp://streamer1.streamhost.org:1935/salive/GMItvfh"),
            // ToDo: Italian, Dutch, Czech & Slovak
            new VideoStream(R.drawable.image0, "Dummy for Italy"),
            new VideoStream(R.drawable.image0, "Dummy for Dutch"),
            new VideoStream(R.drawable.image0, "Dummy for Slovak & Czech")
            
            
        };

        VideoStreamAdapter adapter = new VideoStreamAdapter(this,
                R.layout.listview_item_row, aVideoStreams);
        
        tvChannelListView = (ListView)findViewById(R.id.listView1);
         
        View header = getLayoutInflater().inflate(R.layout.listview_header_row, null);
        tvChannelListView.addHeaderView(header);
        
        tvChannelListView.setAdapter(adapter);
      
        tvChannelListView.setOnItemClickListener(new OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parentAdapter, View view, final int tvPosition, long id) {

                int[] numOptions = new int[1];
                numOptions[0] = 0;
                ArrayList<Boolean> resolOptions = getResOptions(tvPosition, numOptions);
/*
                switch (tvPosition) // ToDo: Link this position to the position in the table (use some DB ?)
                {
                    case 1: // HMission TV
                        resolOptions = new ArrayList<Boolean>() {
                            {
                                add(aVideoStreams[tvPosition].urlLo != null);
                                add(aVideoStreams[tvPosition].urlMed != null);
                                add(aVideoStreams[tvPosition].urlHi != null);
                            }
                        };
                        numOptions = 3; // Number of true items
                        break;

                    case 2: // HCBN Philippines
                        resolOptions = new ArrayList<Boolean>() {
                            {
                                add(true);
                                add(true);
                                add(false);
                            }
                        };
                        numOptions = 2; // Number of true items
                        break;

                    case 3: // Global Family Network
                        resolOptions = new ArrayList<Boolean>() {
                            {
                                add(false);
                                add(false);
                                add(true);
                            }
                        };
                        numOptions = 1; // Number of true items
                        break;

                    case 0:
                    default:
                        // do nothing
                        assert (false);
                        break;
                } // switch (position)
*/
                if (!resolOptions.isEmpty())
                    launchVideo(resolOptions, numOptions[0], tvPosition, Video_Low_Mission.class);

            }

        });
        
    }

    ArrayList<Boolean> getResOptions(final int selectedTV, int [] numOptions)
    {
        ArrayList<Boolean> resolOptions = new ArrayList<Boolean>() {
             {
                add(aVideoStreams[selectedTV].urlLo == null);
                add(aVideoStreams[selectedTV].urlMed == null);
                add(aVideoStreams[selectedTV].urlHi == null);
            }
        };

        numOptions[0] = 0;

        for (Iterator<Boolean> i = resolOptions.iterator() ; i.hasNext() ; )
        {
            boolean element = i.next();
            if(element)
                numOptions[0]++;
        }

        return resolOptions;
    }

/*
    int numOptions getResOptionsStrings(final int selectedTV)
    {
        int idx = 0;
        CharSequence[] resOptsItems = new CharSequence[numResOptions];
        if ( aVideoStreams[selectedTV].urlLo != null )
            resOptsItems[idx++] = "Low Resolution";

        if ( aVideoStreams[selectedTV].urlMed != null )
            resOptsItems[idx++] = "Medium Resolution";

        if ( aVideoStreams[selectedTV].urlHi != null )
            resOptsItems[idx] = "High Resolution";

    }
*/

    private boolean launchVideo (List<Boolean> resolOptions, int numResOptions, final int selectedTV, final Class ministryClass)
    {

        CharSequence[] resOptsItems = new CharSequence[numResOptions];
        final CharSequence[] itemsMuster = {"Low", "Medium", "High"};

        int idxQM = 0, idxMuster = 0;
        for (Iterator<Boolean> i = resolOptions.iterator() ; i.hasNext() ; )
        {
            boolean element = i.next();
            if(element == true)
            {
                resOptsItems[idxQM] = itemsMuster[idxMuster];
                idxQM += 1;
            }
            idxMuster += 1;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Please choose video quality.");
        //builder.setIcon(R.drawable.image1);
        builder.setItems(resOptsItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int selectedResolution) {

                Intent intent = null;
                /*
                switch(selectedResolution){

                    case 0:
                        intent = new Intent(getApplicationContext(), ministryClass);
                        intent.putExtra("Chosen Resolution", selectedResolution); //intent.setFlags(selectedResolution);
                        break;
                    case 1:
                        //intent = new Intent(getApplicationContext(), Video_Med_Mission.class);
                        intent = new Intent(getApplicationContext(), ministryClass);
                        intent.putExtra("Chosen Resolution", selectedResolution); //intent.setFlags(selectedResolution);
                        break;
                    case 2:
                        //intent = new Intent(getApplicationContext(), Video_High_Mission.class);
                        intent = new Intent(getApplicationContext(), ministryClass);
                        intent.putExtra("Chosen Resolution", selectedResolution); //intent.setFlags(selectedResolution);
                        break;
                    default:
                        // Must never happen !
                        assert(false);
                        break;
                }
                */

                intent = new Intent(getApplicationContext(), ministryClass);
                //Pass video resolution to the new activity
                intent.putExtra(CHOSEN_TV, selectedTV); //intent.putExtra("Chosen TV", selectedTV);
                //Pass video resolution to the new activity
                intent.putExtra(CHOSEN_RESOLUTION, selectedResolution); //intent.putExtra("Chosen Resolution", selectedResolution);

                if(intent!=null) startActivity(intent);
            }
        });

        AlertDialog alert = builder.create();

        alert.show();
        return true;
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_MENU:
                // Do SOMETHING
            	
            	final CharSequence[] items = {"TV Stations", "Radio Stations", "About GMI"};

            	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            	builder.setTitle("Menu");
            	//builder.setIcon(R.drawable.image1);
            	builder.setItems(items, new DialogInterface.OnClickListener() {
            	    @Override
					public void onClick(DialogInterface dialog, int item) {
            	    	
            	    	if(item == 0){
            	    		
            	    		//Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
            	    		Intent i = new Intent(getApplicationContext(), MainActivity.class);
            	    		startActivity(i);
            	    	}
            	    	else if(item == 1){
            	    	
            	    		Intent i = new Intent(getApplicationContext(), RadioActivity.class);
            	    		startActivity(i);
            	    	}
            	    	else if(item == 2){
            	    		
            	    		
            	    		Intent i = new Intent(getApplicationContext(), Info.class);
            	    		startActivity(i);
            	    	}
            	    	
            	    	
            	
            	    }
            	});
            	AlertDialog alert = builder.create();

            	alert.show();
            	
                return true;
        }

        return super.onKeyDown(keycode, e);
        
        
        
    }
     
    
    @Override
    
        public boolean onCreateOptionsMenu(Menu menu) {
    
            // use an inflater to populate the ActionBar with items
    
            MenuInflater inflater = getMenuInflater();
    
            inflater.inflate(R.menu.main, menu);
    
           return true;
    
        }
    
         
    
        @Override
    
        public boolean onOptionsItemSelected(MenuItem item){
    
            // same as using a normal menu
    
            switch(item.getItemId()) {
    
            case R.id.item_more:
    
            	// Do your ***
            	
            	
            	
            	final CharSequence[] items = {"TV Stations", "Radio Stations", "Information"};

            	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            	builder.setTitle("Menu");
            	//builder.setIcon(R.drawable.image1);
            	builder.setItems(items, new DialogInterface.OnClickListener() {
            	    @Override
					public void onClick(DialogInterface dialog, int item) {
            	    	
            	    	if(item == 0){
            	    		
            	    		//Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
            	    		Intent i = new Intent(getApplicationContext(), MainActivity.class);
            	    		startActivity(i);
            	    	}
            	    	else if(item == 1){
            	    	
            	    		Intent i = new Intent(getApplicationContext(), RadioActivity.class);
            	    		startActivity(i);
            	    	}
            	    	if(item == 2){
            	    		
            	    		
            	    		Intent i = new Intent(getApplicationContext(), Info.class);
            	    		startActivity(i);
            	    	}
            	    	
            	    	
            	
            	    }
            	});
            	AlertDialog alert = builder.create();

            	alert.show();
            	
               
                break;
           }   

            return true;
        }
    
         
    
       
}
