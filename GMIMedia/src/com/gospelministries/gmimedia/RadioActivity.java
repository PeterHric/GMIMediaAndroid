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
import android.widget.Toast;

public class RadioActivity extends Activity {

	
    private ListView listView1;


    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setTitle("Gospel Ministries");
        
        ViedoStream viedoStream_data[] = new ViedoStream[]
        {
            new ViedoStream(R.drawable.image12, "Radio La Voz"),
            new ViedoStream(R.drawable.image2, "GFN Radio"),
            new ViedoStream(R.drawable.image4, "Radio Altiplano"),
            new ViedoStream(R.drawable.image9, "Terceiro Anjo")
           
          
        };
        
        ViedoStreamAdapter adapter = new ViedoStreamAdapter(this, 
                R.layout.listview_item_row, viedoStream_data);
        
        
        listView1 = (ListView)findViewById(R.id.listView1);
         
        View header = getLayoutInflater().inflate(R.layout.listview_header_row_radio, null);
        listView1.addHeaderView(header);
        
        listView1.setAdapter(adapter);
      
        listView1.setOnItemClickListener(new OnItemClickListener() {

            @Override
			public void onItemClick(AdapterView <?> parentAdapter, View view, int position,
                                    long id) {

                
            	if(position == 0){
    	    		
            		// NOTHING
    	    		
    	    	}
            	if(position == 1){
    	    		
            		final CharSequence[] itemsQM = {"Low", "Medium", "High"};

                	AlertDialog.Builder builder = new AlertDialog.Builder(RadioActivity.this);
                	builder.setTitle("Select ");
                	//builder.setIcon(R.drawable.image1);
                	builder.setItems(itemsQM, new DialogInterface.OnClickListener() {
                	    @Override
						public void onClick(DialogInterface dialog, int itemMissionTV) {
                	    	
                	    	if(itemMissionTV == 0){
                	    		
                	    		Intent i = new Intent(getApplicationContext(), Video_Low_Mission.class);
                	    		startActivity(i);
                	    		
                	    	}
                	    	else if(itemMissionTV == 1){
                	    	
                	    		
                	    	}
                	    	if(itemMissionTV == 2){
                	    		
                	    		
                	    		
                	    	}
                	    	
                	    	
                	
                	    }
                	});
                	AlertDialog alert = builder.create();

                	alert.show();
                	
                    
            
    	    	}
            	if(position == 2){
	
	
            	}
            	if(position == 3){
	
	
            	}
            	if(position == 4){
	
	
            	}
            	
           
            }
       });
       
        
        
    }
    
    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_MENU:
                // Do SOMETHING
            	
            	final CharSequence[] items = {"TV Stations", "Radio Stations", "Information"};

            	AlertDialog.Builder builder = new AlertDialog.Builder(RadioActivity.this);
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

            	AlertDialog.Builder builder = new AlertDialog.Builder(RadioActivity.this);
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
