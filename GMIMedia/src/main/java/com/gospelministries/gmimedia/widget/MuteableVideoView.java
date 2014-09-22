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

package com.gospelministries.gmimedia.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.AttributeSet;
import android.widget.VideoView;
//import android.widget.MediaController;

/**
 * TODO: implement
 *
 * @since 0.9_beta
 */
public class MuteableVideoView extends VideoView implements OnPreparedListener,
    OnCompletionListener, OnErrorListener {

    private MediaPlayer mediaPlayer;

    public MuteableVideoView(Context context, AttributeSet attributes) {
        super(context, attributes);

        this.setOnPreparedListener(this);
        this.setOnCompletionListener(this);
        this.setOnErrorListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        // TODO
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        // TODO
    }

    public void mute() {
        this.setVolume(0);
    }

    public void unmute() {
        this.setVolume(100);
    }

    // This can be declared public in order to gain full volume control
    private void setVolume(int amount) {
        final int max = 100;
        final double numerator = max - amount > 0 ? Math.log(max - amount) : 0;
        final float volume = (float) (1 - (numerator / Math.log(max)));

        this.mediaPlayer.setVolume(volume, volume);
    }
}
