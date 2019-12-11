package com.ntmhien.ailatrieuphu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HienThiCauHoi extends AppCompatActivity {
    QuanLiCauHoi quanLiCauHoi;

    TextView txtSttCau;
    TextView txtCauHoi;
    Button btnPanA;
    Button btnPanB;
    Button btnPanC;
    Button btnPanD;

    int stt=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cauhoi);

        Log.v("Log", GetContentDataQuestion());

        try {
            quanLiCauHoi = QuanLiCauHoi.CreateByJSON(GetContentDataQuestion());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtSttCau = findViewById(R.id.txtSttCau);
        txtCauHoi = findViewById(R.id.txtCauHoi);
        btnPanA = findViewById(R.id.btnPhuongAnA);
        btnPanB = findViewById(R.id.btnPhuongAnB);
        btnPanC = findViewById(R.id.btnPhuongAnC);
        btnPanD = findViewById(R.id.btnPhuongAnD);

        OnNext(null);
    }

    public void OnNext(View view){
        if(quanLiCauHoi.hasNext()){
            resetUI(quanLiCauHoi.next());
        }
    }

    public void resetUI(CauHoi cauHoi){
        if(stt<=10)
        {
            txtSttCau.setText(""+(stt++));
            txtCauHoi.setText(cauHoi.getCauhoi());
            btnPanA.setText("A. "+cauHoi.getPhuonganA());
            btnPanB.setText("B. "+cauHoi.getPhuonganB());
            btnPanC.setText("C. "+cauHoi.getPhuonganC());
            btnPanD.setText("D. "+cauHoi.getPhuonganD());
        }
        else
        {
            AlertDialog.Builder b=new AlertDialog.Builder(HienThiCauHoi.this);
            b.setTitle("Hoàn thành lượt chơi");
            b.setMessage("Bạn có muốn thoát?");
            b.setPositiveButton("Thoát", new DialogInterface. OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    finish();
                }});b.setNegativeButton("Chơi lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
                startActivity(getIntent());
            }
        });
            b.create().show();
        }
    }

    public String GetContentDataQuestion(){
        InputStream inputStream = getResources().openRawResource(R.raw.cauhoi);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }
}