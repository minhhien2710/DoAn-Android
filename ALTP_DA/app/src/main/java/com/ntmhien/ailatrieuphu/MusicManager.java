package com.ntmhien.ailatrieuphu;

import android.app.Activity;
import android.media.MediaPlayer;

import java.util.ArrayList;

public class MusicManager{
    ArrayList<Music> musicArrayList;
    MediaPlayer mediaPlayer;

    public void addMusic(){
        musicArrayList = new ArrayList<>();

        musicArrayList.add(new Music("0-NhacNen",R.raw.bgmusic));
        musicArrayList.add(new Music("1-NhacStartGame",R.raw.gofind));
    }

    public void setNhacNen(Activity activity){
        addMusic();

        mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(0).getFile());
        mediaPlayer.start();
    }

    public void setNhacBatDauGame(Activity activity){
        addMusic();

        mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(1).getFile());
        mediaPlayer.start();
    }
}