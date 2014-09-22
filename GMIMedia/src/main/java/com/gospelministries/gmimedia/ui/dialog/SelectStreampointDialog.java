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
import android.content.DialogInterface;
import com.gospelministries.gmimedia.R;
import com.gospelministries.gmimedia.bean.Stream;
import com.gospelministries.gmimedia.bean.Streampoint;

/**
 * Dialog to select a {@link com.gospelministries.gmimedia.bean.Streampoint}
 * from a given {@link com.gospelministries.gmimedia.bean.Stream}.
 *
 * @since 1.0.0
 */
public class SelectStreampointDialog {

    /**
     * The context of the dialog.
     */
    private Context context;

    /**
     * The given {@link com.gospelministries.gmimedia.bean.Stream}.
     */
    private Stream stream;

    /**
     * Callback for clicks.
     */
    private DialogInterface.OnClickListener listener;

    /**
     * The created dialog.
     */
    private AlertDialog dialog;

    /**
     * Creates a new dialog.
     *
     * @param context   The context where the dialog will be shown.
     * @param stream    The given stream.
     */
    public SelectStreampointDialog(Context context, Stream stream, DialogInterface.OnClickListener listener) {
        this.context = context;
        this.stream = stream;
        this.listener = listener;
    }

    /**
     * Shows the dialog.
     */
    public void show() {
        createDialog().show();
    }

    /**
     * Creates the dialog.
     *
     * @return the dialog
     */
    private AlertDialog createDialog() {
        if (dialog != null) return dialog;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.select_streampoint_dialog_title))
               .setItems(getItems(), listener);

        return dialog = builder.create();
    }

    /**
     * Creates the items in the dialog.
     *
     * @return the items
     */
    private CharSequence[] getItems() {
        CharSequence[] items = new CharSequence[stream.getStreampointList().size()];

        int i = 0;
        for (Streampoint streampoint : stream.getStreampointList()) {
            items[i] = streampoint.getQualityString();
            i++;
        }

        return items;
    }
}
