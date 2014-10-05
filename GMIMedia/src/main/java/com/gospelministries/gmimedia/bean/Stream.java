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
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stream been.
 *
 * @since 1.0.0
 */
@Root
public class Stream implements Parcelable {

    @Element
    private String name;

    @Attribute(empty = "flag_none", required = false)
    private String icon;

    @Element
    private String language;

    @ElementList(inline = true)
    private List<Streampoint> streampointList;

    public Stream() {
        this.streampointList = new ArrayList<Streampoint>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Streampoint> getStreampointList() {
        return streampointList;
    }

    public void setStreampointList(List<Streampoint> streampointList) {
        this.streampointList = streampointList;
    }

    public void sortStreampointList() {
        Collections.sort(streampointList);
    }

    public boolean isAvailable() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stream stream = (Stream) o;

        if (icon != null ? !icon.equals(stream.icon) : stream.icon != null)
            return false;
        if (language != null ? !language.equals(stream.language) : stream.language != null)
            return false;
        if (name != null ? !name.equals(stream.name) : stream.name != null)
            return false;
        if (streampointList != null ? !streampointList.equals(stream.streampointList) : stream.streampointList != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (streampointList != null ? streampointList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Stream{" +
            "name='" + name + '\'' +
            ", icon=" + icon +
            ", language=" + language +
            ", streampointList=" + streampointList +
            '}';
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(icon);
        dest.writeString(language);
        dest.writeList(streampointList);
    }

    public static final Creator<Stream> CREATOR = new Creator<Stream>() {
        @Override
        public Stream createFromParcel(Parcel source) {
            Stream stream = new Stream();

            stream.setName(source.readString());
            stream.setIcon(source.readString());
            stream.setLanguage(source.readString());
            stream.setStreampointList(source.readArrayList(Streampoint.class.getClassLoader()));

            return stream;
        }

        @Override
        public Stream[] newArray(int size) {
            return new Stream[size];
        }
    };
}
