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

package com.gospelministries.gmimedia.util;

import android.content.Context;
import com.gospelministries.gmimedia.bean.StreamList;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;

/**
 * Helperclass for de-/serialization.
 *
 * @since 1.0.0
 */
public class SerializerHelper {

    /**
     * Common serializer instance.
     */
    private final static Serializer SERIALIZER = new Persister();

    /**
     * Deserializes a {@link com.gospelministries.gmimedia.bean.StreamList}
     * stored in an XML file.
     *
     * @param context   The context of the resource.
     * @param rawId     The ID of the resource.
     *
     * @return The deserialized StreamList.
     *
     * @throws Exception
     */
    public static StreamList deserializeStreamList(Context context, int rawId) throws Exception {
        InputStream input = context.getResources().openRawResource(rawId);
        return SERIALIZER.read(StreamList.class, input);
    }
}
