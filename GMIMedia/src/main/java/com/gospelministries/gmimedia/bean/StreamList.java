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

package com.gospelministries.gmimedia.bean;

import android.os.Parcel;
import android.os.Parcelable;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of {@link com.gospelministries.gmimedia.bean.Stream}.
 *
 * @since 1.0.0
 */
@Root
public class StreamList implements Parcelable {

    @ElementListUnion({
        @ElementList(entry = "video-stream", type = VideoStream.class, inline = true),
        @ElementList(entry = "audio-stream", type = AudioStream.class, inline = true)
    })
    private List<Stream> streams;

    public StreamList() {
        this.streams = new ArrayList<Stream>();
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StreamList that = (StreamList) o;

        if (streams != null ? !streams.equals(that.streams) : that.streams != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return streams != null ? streams.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StreamList{" +
            "streams=" + streams +
            '}';
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(streams);
    }

    public static final Creator<StreamList> CREATOR = new Creator<StreamList>() {
        @Override
        public StreamList createFromParcel(Parcel source) {
            StreamList streamList = new StreamList();

            streamList.setStreams(source.readArrayList(Stream.class.getClassLoader()));

            return streamList;
        }

        @Override
        public StreamList[] newArray(int size) {
            return new StreamList[size];
        }
    };
}
