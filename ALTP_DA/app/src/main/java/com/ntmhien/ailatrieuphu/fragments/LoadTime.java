package com.ntmhien.ailatrieuphu.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ntmhien.ailatrieuphu.R;
import com.ntmhien.ailatrieuphu.activity.HienThiCauHoi;
import com.ntmhien.ailatrieuphu.model.CauHinhApp;
import com.ntmhien.ailatrieuphu.model.LinhVuc;
import com.ntmhien.ailatrieuphu.music.MusicManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoadTime extends AsyncTask<Integer,Integer,Void> {

    private ProgressBar progressBar;
    private TextView textView;
    Activity contextParent;
    MusicManager musicManager;

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
        b.setTitle("Hết thời gian! ");
        b.setMessage("Rất tiếc bạn đã hết thời gian trả lời! ");
        b.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                musicManager.setNhacThuaCuoc(contextParent);
                contextParent.finish();
            }
        });
        b.create().show();
    }
}