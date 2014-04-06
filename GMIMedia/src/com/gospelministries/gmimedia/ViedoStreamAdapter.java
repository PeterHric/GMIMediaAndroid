package com.gospelministries.gmimedia;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ViedoStreamAdapter extends ArrayAdapter<ViedoStream>{

	 Context context; 
	    int layoutResourceId;    
	    ViedoStream data[] = null;
	    
	    public ViedoStreamAdapter(Context context, int layoutResourceId, ViedoStream[] data) {
	        super(context, layoutResourceId, data);
	        this.layoutResourceId = layoutResourceId;
	        this.context = context;
	        this.data = data;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View row = convertView;
	        ViedoStreamHolder holder = null;
	        
	        if(row == null)
	        {
	            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(layoutResourceId, parent, false);
	            
	            holder = new ViedoStreamHolder();
	            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
	            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
	            
	            row.setTag(holder);
	        }
	        else
	        {
	            holder = (ViedoStreamHolder)row.getTag();
	        }
	        
	        ViedoStream viedoStream = data[position];
	        holder.txtTitle.setText(viedoStream.title);
	        holder.imgIcon.setImageResource(viedoStream.icon);
	        
	        return row;
	    }
	    
	    static class ViedoStreamHolder
	    {
	        ImageView imgIcon;
	        TextView txtTitle;
	    }
	
}
