package com.ntmhien.ailatrieuphu.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ntmhien.ailatrieuphu.R;
import com.ntmhien.ailatrieuphu.activity.HienThiCauHoi;
import com.ntmhien.ailatrieuphu.music.MusicManager;

public class LoadTime extends AsyncTask<Integer,Integer,Void> {

    private ProgressBar progressBar;
    private TextView textView;
    Activity contextParent;
    MusicManager musicManager;
    HienThiCauHoi hienThiCauHoi;

    public LoadTime(Activity contextParent) {
        this.contextParent = contextParent;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        try{
            for(int i=30;i>=0;i--){
                Thread.sleep(1000);
                publishProgress(i);
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        progressBar=contextParent.findViewById(R.id.timeProgressBar);
        textView=contextParent.findViewById(R.id.txtTime);

        int number= values[0];
        textView.setText(number+"");
        progressBar.setProgress(number);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        musicManager = new MusicManager();

        musicManager.setNhacHetGio(contextParent);
        progressBar.setVisibility(ProgressBar.GONE);

        Toast.makeText(contextParent, "Ôi không! Bạn đã hết thời gian trả lời!", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder b = new AlertDialog.Builder(contextParent);
        b.setTitle("Tổng điểm của bạn là: "+hienThiCauHoi.getPoint());
        b.setIcon(R.drawable.profile_icon_money);
        b.setMessage("Bạn có muốn sử dụng 500 Credit để chơi tiếp hay không? ");
        b.setNegativeButton("Sử dụng Credit", new DialogInterface. OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                hienThiCauHoi.updatePoint();
                musicManager.setNhacCauHoiTiepTheo(contextParent);
                //Câu kế tiếp
                hienThiCauHoi.setCauTiepTheo();
            }});
        b.setPositiveButton("Dừng cuộc chơi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                musicManager.setNhacThuaCuoc(contextParent);
                contextParent.finish();
            }
        });
        b.create().show();
    }
}