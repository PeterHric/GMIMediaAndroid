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

	
    private ListView listView1;
    public final static String CHOSEN_TV = "com.gospelministries.gmimedia.TV";
    public final static String CHOSEN_RESOLUTION = "com.gospelministries.gmimedia.RESOLUTION";

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        setTitle("Gospel Ministries");
        
        VideoStream videoStream_data[] = new VideoStream[]
        {
            new VideoStream(R.drawable.image0, "Mission TV"),
            new VideoStream(R.drawable.image1, "HCBN Philippines"),
            new VideoStream(R.drawable.image2, "Global Family Network"),
            new VideoStream(R.drawable.image3, "2CBN"),
            new VideoStream(R.drawable.image4, "Red ADvenir"),
            new VideoStream(R.drawable.image5, "Alfa Television"),
            new VideoStream(R.drawable.image6, "Light Channel Hungary"),
            new VideoStream(R.drawable.image7, "Light Channel Rumania"),
            new VideoStream(R.drawable.image8, "Light Channel Germany"),
            new VideoStream(R.drawable.image9, "Terceiro Anjo"),
            new VideoStream(R.drawable.image10, "HCBN Indonesia"),
            new VideoStream(R.drawable.image11, "TV Famille")
            
            
            
            
        };
        
        VideoStreamAdapter adapter = new VideoStreamAdapter(this,
                R.layout.listview_item_row, videoStream_data);
        
        
        listView1 = (ListView)findViewById(R.id.listView1);
         
        View header = getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView1.addHeaderView(header);
        
        listView1.setAdapter(adapter);
      
        listView1.setOnItemClickListener(new OnItemClickListener() {

        	
        	
            @Override
			public void onItemClick(AdapterView <?> parentAdapter, View view, int position, long id) {

                ArrayList<Boolean> resolOptions = null;
                int numOptions = 0;
                switch(position) // ToDo: Link this position to the position in the table (use some DB ?)
                {
                    case 1: // HMission TV                            Low  ,   Medium   ,  High
                        resolOptions = new ArrayList<Boolean> () { { add(true); add(true); add(true);} };
                        numOptions = 3; // Number of true items
                        break;

                    case 2: // HCBN Philippines                        Low  ,   Medium   ,  High
                        resolOptions = new ArrayList<Boolean> () { { add(true); add(true); add(false);} };
                        numOptions = 2; // Number of true items
                        break;

                    case 3: // Global Family Network
                        resolOptions = new ArrayList<Boolean> () { { add(false); add(false); add(true);} };
                        numOptions = 1; // Number of true items
                        break;

                    case 0:
                    default:
                        // do nothing
                        assert(false);
                        break;
                } // switch (position)

                if(!resolOptions.isEmpty())
                    launchVideo( resolOptions , numOptions , position, Video_Low_Mission.class);

            }

       });
        
    }


    private boolean launchVideo (List<Boolean> resolOptions, int numResOptions, final int selectedTV, final Class ministryClass)
    {
        final CharSequence[] itemsMuster = {"Low", "Medium", "High"};
        CharSequence[] itemsQM = new CharSequence[numResOptions];
        int idxQM = 0, idxMuster = 0;
        for (Iterator<Boolean> i = resolOptions.iterator() ; i.hasNext() ; )
        {
            boolean element = i.next();
            if(element == true)
            {
                itemsQM[idxQM] = itemsMuster[idxMuster];
                idxQM += 1;
            }
            idxMuster += 1;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Please choose video quality.");
        //builder.setIcon(R.drawable.image1);
        builder.setItems(itemsQM, new DialogInterface.OnClickListener() {
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
