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
import android.support.annotation.NonNull;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Streampoint been.
 *
 * @since 1.0.0
 */
@Root
public class Streampoint implements Parcelable, Comparable<Streampoint> {

    @Attribute
    private int bitrate;

    @Text
    private String url;

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQualityString() {
        String qualityString;

        // TODO: translate + choose bitrates
        // TODO: adapt for audio
        if      (1000 <= bitrate)                   qualityString = "Hi: " + bitrate + " kbps";
        else if ( 500 <= bitrate && bitrate < 1000) qualityString = "Med: " + bitrate + " kbps" ;
        else                                        qualityString = "Lo: " + bitrate + " kbps";

        return qualityString;
    }

    @Override
    public int compareTo(@NonNull Streampoint another) {
        return new Integer(bitrate).compareTo(another.getBitrate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Streampoint that = (Streampoint) o;

        if (bitrate != that.bitrate) return false;
        if (url != null ? !url.equals(that.url) : that.url != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bitrate;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Streampoint{" +
            "bitrate=" + bitrate +
            ", url='" + url + '\'' +
            '}';
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bitrate);
        dest.writeString(url);
    }

    public static final Creator<Streampoint> CREATOR = new Creator<Streampoint>() {
        @Override
        public Streampoint createFromParcel(Parcel source) {
            Streampoint streampoint = new Streampoint();

            streampoint.setBitrate(source.readInt());
            streampoint.setUrl(source.readString());

            return streampoint;
        }

        @Override
        public Streampoint[] newArray(int size) {
            return new Streampoint[size];
        }
    };
}
