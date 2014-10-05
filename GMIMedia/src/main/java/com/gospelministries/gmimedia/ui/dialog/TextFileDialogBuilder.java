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

package com.gospelministries.gmimedia.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;

import java.io.*;

/**
 * Dialog builder where the message is read from a file.
 *
 * @since 1.0.0
 */
public class TextFileDialogBuilder {

    /**
     * The context of the dialog. From here the Strings will be pulled.
     */
    private Context context;

    /**
     * The resource ID of the text file.
     */
    private int rawTextFileId;

    /**
     * Creates a new dialog.
     *
     * @param context       The context where the dialog will be shown. From
     *                      here the Strings will be pulled.
     * @param rawTextFileId The ID of the text file. It should be located in the
     *                      `raw` directory.
     */
    public TextFileDialogBuilder(Context context, int rawTextFileId) {
        this.context = context;
        this.rawTextFileId = rawTextFileId;
    }

    /**
     * Returns the builder
     *
     * @return an {@link android.app.AlertDialog.Builder} for more customizations.
     *
     * @throws IllegalStateException
     */
    public AlertDialog.Builder getAlertDialogBuilder() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        try {
            alertDialogBuilder.setMessage(readTextFile());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return alertDialogBuilder;
    }

    /**
     * Reads the text file and sets it.
     *
     * @return the content of the text file.
     *
     * @throws IOException
     */
    private String readTextFile() throws IOException {
        InputStream inputStream = context.getResources().openRawResource(rawTextFileId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringWriter stringWriter = new StringWriter();

        String line;
        while ((line = reader.readLine()) != null) {
            stringWriter.append(line).append("\n");
        }

        return stringWriter.toString();
    }
}
