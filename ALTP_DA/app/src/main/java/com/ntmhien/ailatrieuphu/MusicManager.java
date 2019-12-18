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
        musicArrayList.add(new Music("2-NhacCauHoiDauTien",R.raw.ques1_b));
        musicArrayList.add(new Music("3-NhacCauHoiTiepTheo",R.raw.question_all));
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
        mediaPlayer.setNextMediaPlayer(MediaPlayer.create(activity,musicArrayList.get(2).getFile()));
    }

    public void setNhacCauHoiTiepTheo(Activity activity){
        addMusic();
        mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(3).getFile());
        mediaPlayer.start();
    }
}