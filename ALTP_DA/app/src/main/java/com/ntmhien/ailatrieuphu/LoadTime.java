package com.ntmhien.ailatrieuphu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoadTime extends AsyncTask<Integer,Integer,Void> {

    private ProgressBar progressBar;
    private TextView textView;
    Activity contextParent;

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
        Toast.makeText(contextParent, "Hết thời gian !", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder b=new AlertDialog.Builder(contextParent);
        b.setTitle("Ôi không! Bạn đã hết thời gian trả lời");
        b.setMessage("Tổng điểm của bạn là: ");
        b.setPositiveButton("Thoát", new DialogInterface. OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                contextParent.finish();
            }});
        b.create().show();
    }
}