package com.ntmhien.ailatrieuphu.music;

import android.app.Activity;
import android.media.MediaPlayer;

import com.ntmhien.ailatrieuphu.R;

import java.util.ArrayList;
import java.util.Random;

public class MusicManager{
    ArrayList<Music> musicArrayList;
    private MediaPlayer mediaPlayer;

    public void addMusic(){
        musicArrayList = new ArrayList<>();

        musicArrayList.add(new Music("0-Nhạc Nền", R.raw.bgmusic,0));
        musicArrayList.add(new Music("1-Nhạc StartGame",R.raw.gofind,0));
        musicArrayList.add(new Music("2-Nhạc STT Câu Hỏi",R.raw.ques1_b,R.raw.question_next));
        //Music Chọn Đáp Án
        musicArrayList.add(new Music("3-Chọn Câu A ",R.raw.ans_a,R.raw.ans_a2));
        musicArrayList.add(new Music("4-Chọn Câu B",R.raw.ans_b,R.raw.ans_b2));
        musicArrayList.add(new Music("5-Chọn Câu C",R.raw.ans_c,R.raw.ans_c2));
        musicArrayList.add(new Music("6-Chọn Câu D",R.raw.ans_d,R.raw.ans_d2));
        //Music Chọn Đáp Án Đúng
        musicArrayList.add(new Music("7-Chọn Câu A Đúng ",R.raw.true_a,R.raw.true_a3));
        musicArrayList.add(new Music("8-Chọn Câu B Đúng",R.raw.true_b,R.raw.true_b2));
        musicArrayList.add(new Music("9-Chọn Câu C Đúng",R.raw.true_c,R.raw.true_c2));
        musicArrayList.add(new Music("10-Chọn Câu D Đúng",R.raw.true_d2,R.raw.true_d3));
        //Music Chuẩn Bị Đọc Đáp Án
        musicArrayList.add(new Music("11-Chuẩn Bị Đọc Đáp Án",R.raw.ans_now1,R.raw.ans_now2));
        //Music Nền Khi Đang Trả Lời
        musicArrayList.add(new Music("12-Nhạc Nền Khi Đang Trả Lời",R.raw.background_music,R.raw.background_music_c));
        //Music Thua Cuộc
        musicArrayList.add(new Music("13-Nhạc Thua Cuộc",R.raw.lose2,0));
        musicArrayList.add(new Music("14-Nhạc Hết Giờ",R.raw.out_of_time,0));
        //Music Đáp Án Đúng Là
        musicArrayList.add(new Music("15-Đáp Án Đúng Là A",R.raw.lose_a,R.raw.lose_a2));
        musicArrayList.add(new Music("16-Đáp Án Đúng Là B",R.raw.lose_b,R.raw.lose_b2));
        musicArrayList.add(new Music("17-Đáp Án Đúng Là C",R.raw.lose_c,R.raw.lose_c2));
        musicArrayList.add(new Music("18-Đáp Án Đúng Là D",R.raw.lose_d,R.raw.lose_d2));
    }

    public void setNhacNen(Activity activity){
        addMusic();
        mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(0).getFile1());
        mediaPlayer.start();
    }

    public void setNhacNenKhiDangTraLoi(Activity activity){
        addMusic();

        Random rd = new Random();
        int rdInt = rd.nextInt(2) + 1;

        if(rdInt==1)mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(12).getFile1());
        else if (rdInt==2)mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(12).getFile2());
        mediaPlayer.start();
    }

    public void setNhacBatDauGame(Activity activity){
        addMusic();
        mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(1).getFile1());
        mediaPlayer.start();
        mediaPlayer.setNextMediaPlayer(MediaPlayer.create(activity,musicArrayList.get(2).getFile1()));
    }

    public void setNhacCauHoiTiepTheo(Activity activity){
        addMusic();
        mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(2).getFile2());
        mediaPlayer.start();
    }

    public void setNhacChonDapAn(Activity activity, final String DapAn){
        addMusic();

        Random rd = new Random();
        int rdInt = rd.nextInt(2) + 1;

        switch (DapAn) {
            case "A":
                if(rdInt==1) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(3).getFile1());
                else if (rdInt==2) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(3).getFile2());
                mediaPlayer.start();
                break;
            case "B":
                if(rdInt==1)mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(4).getFile1());
                else if (rdInt==2)mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(4).getFile2());
                mediaPlayer.start();
                break;
            case "C":
                if(rdInt==1)mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(5).getFile1());
                else if (rdInt==2)mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(5).getFile2());
                mediaPlayer.start();
                break;
            case "D":
                if(rdInt==1)mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(6).getFile1());
                else if (rdInt==2)mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(6).getFile2());
                mediaPlayer.start();
                break;
            default:
                break;
        }
    }

    public void setNhacChonDapAnDung(Activity activity, final String DapAn){
        addMusic();

        Random rd = new Random();
        int rdInt = rd.nextInt(2) + 1;

        switch (DapAn) {
            case "A":
                if(rdInt==1) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(7).getFile1());
                else if (rdInt==2) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(7).getFile2());
                mediaPlayer.start();
                break;
            case "B":
                if(rdInt==1) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(8).getFile1());
                else if (rdInt==2) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(8).getFile2());
                mediaPlayer.start();
                break;
            case "C":
                if(rdInt==1) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(9).getFile1());
                else if (rdInt==2) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(9).getFile2());
                mediaPlayer.start();
                break;
            case "D":
                if(rdInt==1) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(10).getFile1());
                else if (rdInt==2) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(10).getFile2());
                mediaPlayer.start();
                break;
            default:
                break;
        }
    }

    public void setNhacChuanBiDocDapAn(Activity activity){
        addMusic();

        Random rd = new Random();
        int rdInt = rd.nextInt(2) + 1;

        if(rdInt==1) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(11).getFile1());
        else if (rdInt==2) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(11).getFile2());
        mediaPlayer.start();
    }

    public void setNhacHetGio(Activity activity){
        addMusic();
        mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(14).getFile1());
        mediaPlayer.start();
    }

    public void setNhacThuaCuoc(Activity activity){
        addMusic();

        Random rd = new Random();
        int rdInt = rd.nextInt(2) + 1;

        if(rdInt==1)mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(13).getFile1());
        else if (rdInt==2)mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(13).getFile2());
        mediaPlayer.start();
    }

    public void setNhacDapAnDungLa(Activity activity, final String DapAn){
        addMusic();

        Random rd = new Random();
        int rdInt = rd.nextInt(2) + 1;

        switch (DapAn) {
            case "A":
                if(rdInt==1) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(15).getFile1());
                else if (rdInt==2) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(15).getFile2());
                mediaPlayer.start();
                break;
            case "B":
                if(rdInt==1) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(16).getFile1());
                else if (rdInt==2) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(16).getFile2());
                mediaPlayer.start();
                break;
            case "C":
                if(rdInt==1) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(17).getFile1());
                else if (rdInt==2) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(17).getFile2());
                mediaPlayer.start();
                break;
            case "D":
                if(rdInt==1) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(18).getFile1());
                else if (rdInt==2) mediaPlayer = MediaPlayer.create(activity,musicArrayList.get(18).getFile2());
                mediaPlayer.start();
                break;
            default:
                break;
        }
    }
}