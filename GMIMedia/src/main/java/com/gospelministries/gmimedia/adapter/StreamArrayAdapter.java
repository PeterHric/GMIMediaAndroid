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

package com.gospelministries.gmimedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.gospelministries.gmimedia.R;
import com.gospelministries.gmimedia.bean.Stream;
import com.gospelministries.gmimedia.bean.StreamList;

/**
 * Displays a list of streams.
 *
 * @since 0.9_beta
 */
public class StreamArrayAdapter extends ArrayAdapter<Stream> {

    /**
     * The ID of the layout.
     */
    private final int layoutResourceId;

    /**
     * Streams to show in the list.
     */
    private final Stream[] streams;

    /**
     * Creates new StreamArrayAdapter
     *
     * @param context           The context to use.
     * @param layoutResourceId  The ID of the layout to use. It should have an {@link android.widget.ImageView} called
     *                          {@code image_view} and an {@link android.widget.TextView} called {@code name_text_view}.
     * @param streamList        The streams to show.
     */
    public StreamArrayAdapter(Context context, int layoutResourceId, StreamList streamList) {
        this(context, layoutResourceId, streamList.getStreams()
            .toArray(new Stream[streamList.getStreams().size()]));
    }


    /**
     * Creates new StreamArrayAdapter
     *
     * @param context           The context to use.
     * @param layoutResourceId  The ID of the layout to use. It should have an {@link android.widget.ImageView} called
     *                          {@code image_view} and an {@link android.widget.TextView} called {@code name_text_view}.
     * @param streams           The streams to show.
     */
    public StreamArrayAdapter(Context context, int layoutResourceId, Stream[] streams) {
        super(context, layoutResourceId, streams);
        this.layoutResourceId = layoutResourceId;
        this.streams = streams;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Stream stream = streams[position];

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                .inflate(layoutResourceId, parent, false);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.image_view);
        TextView name = (TextView) convertView.findViewById(R.id.name_text_view);

        icon.setImageResource(getContext().getResources().getIdentifier(
            stream.getIcon(), "drawable", getContext().getPackageName()));
        name.setText(stream.getName());

        return convertView;
    }
}
