package com.ntmhien.ailatrieuphu;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

public class MusicManager implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayer;
    private Context c;

    public MusicManager(Context contex) {
        this.c = contex;
    }

    public void playNhacNen(Activity a){
        mediaPlayer = MediaPlayer.create(a,R.raw.bgmusic);
        mediaPlayer.start();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
    }
}