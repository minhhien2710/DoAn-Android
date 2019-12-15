package com.ntmhien.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HienThiCauHoi extends AppCompatActivity {
    private ProgressBar progressBar;
    LoadTime loadTime;
    ArrayList<CauHoi> lst_cauhoi;
    int point = 0;
    int cur = 0;
    TextView m_txt_num;
    TextView m_txt_content;
    TextView m_DA1;
    TextView m_DA2;
    TextView m_DA3;
    TextView m_DA4;

    TextView textView;

    int pos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cauhoi);
        m_txt_num = (TextView) findViewById(R.id.txtSoCau);
        m_txt_content = (TextView) findViewById(R.id.txtCauHoi);
        m_DA1 = findViewById(R.id.A);
        m_DA2 = findViewById(R.id.B);
        m_DA3 = findViewById(R.id.C);
        m_DA4 = findViewById(R.id.D);
        progressBar = findViewById(R.id.timeProgressBar);

        //Load thời gian
        if(loadTime==null)
        {
            loadTime=new LoadTime(this);
            loadTime.execute();
        }
        else if(loadTime.getStatus()==LoadTime.Status.FINISHED)
        {
            loadTime=new LoadTime(this);
            loadTime.execute();
        }


        Intent intent = getIntent();
        String jSonString = intent.getStringExtra("message");
        if (get_lst_cauhoi(jSonString) == true) {
            m_txt_num.setText("Câu: 1");
            m_txt_content.setText(lst_cauhoi.get(0).NoiDung);
            m_DA1.setText("A. " + lst_cauhoi.get(0).PhuongAn1);
            m_DA2.setText("B. " + lst_cauhoi.get(0).PhuongAn2);
            m_DA3.setText("C. " + lst_cauhoi.get(0).PhuongAn3);
            m_DA4.setText("D. " + lst_cauhoi.get(0).PhuongAn4);
        } else {
            m_txt_content.setText("API không hoạt động.");
            m_txt_num.setVisibility(View.INVISIBLE);
            m_DA1.setVisibility(View.INVISIBLE);
            m_DA2.setVisibility(View.INVISIBLE);
            m_DA3.setVisibility(View.INVISIBLE);
            m_DA4.setVisibility(View.INVISIBLE);
        }
    }


    public void ShowQuestion(int pos) {
        int dem = pos + 1;
        m_txt_num.setText("Câu: " + dem);
        m_txt_content.setText(lst_cauhoi.get(pos).NoiDung);
        m_DA1.setText("A. " + lst_cauhoi.get(pos).PhuongAn1);
        m_DA2.setText("B. " + lst_cauhoi.get(pos).PhuongAn2);
        m_DA3.setText("C. " + lst_cauhoi.get(pos).PhuongAn3);
        m_DA4.setText("D. " + lst_cauhoi.get(pos).PhuongAn4);
    }

    private Boolean get_lst_cauhoi(String jSonString) {
        try {
            lst_cauhoi = new ArrayList();
            JSONArray jr = new JSONArray(jSonString);
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject jb = (JSONObject) jr.getJSONObject(i);
                CauHoi quiz = new CauHoi();
                quiz.NoiDung = jb.getString("NoiDung");
                quiz.PhuongAn1 = jb.getString("DapAn1");
                quiz.PhuongAn2 = jb.getString("DapAn2");
                quiz.PhuongAn3 = jb.getString("DapAn3");
                quiz.PhuongAn4 = jb.getString("DapAn4");
                quiz.DapAn = jb.getString("DA_Dung");
                quiz.Chon = "0";
                lst_cauhoi.add(quiz);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void XuLiChonDapAn(final View v) {
        //Dialog verify
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setTitle("Bạn có chắc chọn đáp án "+v.getId()+" không?");
        b.setMessage("Suy nghĩ thật kĩ nhé!");
        b.setPositiveButton("Không chọn", new DialogInterface. OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(HienThiCauHoi.this, "Thời gian vẫn đang chạy đấy nhé!", Toast.LENGTH_SHORT).show();
            }});
        b.setNegativeButton("Vẫn chọn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Xử lý đúng sai
                if(String.valueOf(v.getId())==lst_cauhoi.get(pos).DapAn){
                    point=point+1;
                }

                //Load lại time
                loadTime.cancel(true);
                loadTime=new LoadTime(HienThiCauHoi.this);
                loadTime.execute();

                //Chuyển câu hỏi
                pos++;
                if (pos >= lst_cauhoi.size()) pos = lst_cauhoi.size() - 1;
                ShowQuestion(pos);
            }});
        b.create().show();
    }
}