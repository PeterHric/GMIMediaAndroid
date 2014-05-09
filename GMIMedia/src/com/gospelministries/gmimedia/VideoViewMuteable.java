package com.gospelministries.gmimedia;

/**
 * Created by Peter Hric on 9.5.2014.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;
//import android.widget.MediaController;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;

public class VideoViewMuteable extends VideoView implements OnPreparedListener, OnCompletionListener, OnErrorListener {
    private MediaPlayer mediaPlayer;

    public VideoViewMuteable(Context context, AttributeSet attributes) {
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
      // ToDo
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
      // ToDo
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
