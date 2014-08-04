package com.gospelministries.gmimedia;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoStreamAdapter extends ArrayAdapter<VideoStream>{

     Context context;
        int layoutResourceId;
        VideoStream data[] = null;

        public VideoStreamAdapter(Context context, int layoutResourceId, VideoStream[] data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            VideoStreamHolder holder = null;

            if(row == null)
            {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new VideoStreamHolder();
                holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
                holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);

                row.setTag(holder);
            }
            else
            {
                holder = (VideoStreamHolder)row.getTag();
            }

            VideoStream videoStream = data[position];
            holder.txtTitle.setText(videoStream.title);
            holder.imgIcon.setImageResource(videoStream.icon);

            return row;
        }

        static class VideoStreamHolder
        {
            ImageView imgIcon;
            TextView txtTitle;
        }

}
