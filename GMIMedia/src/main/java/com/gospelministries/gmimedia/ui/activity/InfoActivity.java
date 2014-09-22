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

package com.gospelministries.gmimedia.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import com.gospelministries.gmimedia.R;
import com.gospelministries.gmimedia.ui.dialog.TextFileDialogBuilder;

/**
 * Activity that shows information about the App and GMI.
 *
 * @since 0.9_beta
 */
public class InfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    /**
     * Callback for "About GMI" button.
     */
    public void aboutButtonClicked(View view) {
        // set up dialog builder
        AlertDialog.Builder alertDialogBuilder = new TextFileDialogBuilder(this, R.raw.gmi_description).getAlertDialogBuilder();

        alertDialogBuilder
            .setTitle(getString(R.string.about_gmi))
            .setCancelable(false)
            .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) { }
            });

        // create and show dialog
        alertDialogBuilder.create().show();
    }

    /**
     * Callback for "Contact Us" button.
     */
    public void contactButtonClicked(View view) {
        Intent it = new Intent(Intent.ACTION_SEND);
        it.putExtra(Intent.EXTRA_EMAIL, getString(R.string.email_address));
        it.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        it.setType("text/plain");
        startActivity(it);
    }

    /**
     * Callback for "Website" button.
     */
    public void websiteButtonClicked(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW,
            Uri.parse(getString(R.string.website_address))));
    }
}
