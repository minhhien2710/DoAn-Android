package com.ntmhien.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ntmhien.ailatrieuphu.model.CauHoi;
import com.ntmhien.ailatrieuphu.fragments.LoadTime;
import com.ntmhien.ailatrieuphu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class HienThiCauHoi extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar progressBar;
    private LoadTime loadTime;
    private ArrayList<CauHoi> lst_cauhoi;
    private int point = 0;
    private TextView m_txt_num;
    private TextView m_txt_content;
    private TextView m_DA1, m_DA2, m_DA3, m_DA4;

    private DrawerLayout dl;
    private ImageView Prof;
    ArrayList<Integer> a = new ArrayList<Integer>();
    List<Integer> number = new ArrayList<Integer>();

    int[] rdQuestion;
    int pos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cauhoi);

        setLoadTime();
        findViewByIds();
        setEvents();
        setCauHoi();
    }

    private void setCauHoi() {
        Intent intent = getIntent();
        String jSonString = intent.getStringExtra("message");
        if (get_lst_cauhoi(jSonString) == true) {
            m_txt_num.setText("Câu: 1");
            m_txt_content.setText(lst_cauhoi.get(number.get(pos)).NoiDung);
            m_DA1.setText("A. " + lst_cauhoi.get(number.get(pos)).PhuongAn1);
            m_DA2.setText("B. " + lst_cauhoi.get(number.get(pos)).PhuongAn2);
            m_DA3.setText("C. " + lst_cauhoi.get(number.get(pos)).PhuongAn3);
            m_DA4.setText("D. " + lst_cauhoi.get(number.get(pos)).PhuongAn4);
        } else {
            m_txt_content.setText("API không hoạt động.");
            m_txt_num.setVisibility(View.INVISIBLE);
            m_DA1.setVisibility(View.INVISIBLE);
            m_DA2.setVisibility(View.INVISIBLE);
            m_DA3.setVisibility(View.INVISIBLE);
            m_DA4.setVisibility(View.INVISIBLE);
        }
    }

    private void setLoadTime() {
        if (loadTime == null) {
            loadTime = new LoadTime(this);
            loadTime.execute();
        } else if (loadTime.getStatus() == LoadTime.Status.FINISHED) {
            loadTime = new LoadTime(this);
            loadTime.execute();
        }
    }

    private void findViewByIds() {
        m_txt_num = (TextView) findViewById(R.id.txtSoCau);
        m_txt_content = (TextView) findViewById(R.id.txtCauHoi);
        m_DA1 = findViewById(R.id.A);
        m_DA2 = findViewById(R.id.B);
        m_DA3 = findViewById(R.id.C);
        m_DA4 = findViewById(R.id.D);
        progressBar = findViewById(R.id.timeProgressBar);

        dl = findViewById(R.id.dlcauhoi);
        Prof = findViewById(R.id.imgAvatar);
    }

    public void ShowQuestion(int pos) {
        int sttcau = pos + 1;
        m_txt_num.setText("Câu: " + sttcau);
        m_txt_content.setText(lst_cauhoi.get(number.get(pos)).NoiDung);
        m_DA1.setText("A. " + lst_cauhoi.get(number.get(pos)).PhuongAn1);
        m_DA2.setText("B. " + lst_cauhoi.get(number.get(pos)).PhuongAn2);
        m_DA3.setText("C. " + lst_cauhoi.get(number.get(pos)).PhuongAn3);
        m_DA4.setText("D. " + lst_cauhoi.get(number.get(pos)).PhuongAn4);
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
            //Random câu hỏi
            /*Random rd = new Random();
            rdQuestion = new int[num];
            int tmp;
            int i=0;
            boolean flag;

            while (i<num){
                tmp = rd.nextInt(num);
                flag=false;

                if (i>0) {
                    for (int y = 0; y <= i; y++) {
                        if (tmp == rdQuestion[y]) {
                            flag=true;
                            break;
                        }
                    }
                }*/
            //Random câu hỏi
            /*for (int i = 1; i <= num; ++i) number.add(i);
            Collections.shuffle(number);*/
            Random rng = new Random(); // Ideally just create one instance globally

            for (int i = 0; i < num; i++)
            {
                while(true)
                {
                    Integer next = rng.nextInt(num);
                    if (!number.contains(next))
                    {
                        // Done for this iteration
                        number.add(next);
                        break;
                    }
                }
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setEvents() {
        m_DA1.setOnClickListener(this);
        m_DA2.setOnClickListener(this);
        m_DA3.setOnClickListener(this);
        m_DA4.setOnClickListener(this);

        Prof.setOnClickListener(this);
    }
    public void Prof(View view){
        dl.openDrawer(Gravity.LEFT);
    }
    @Override
    public void onClick(final View v) {
        //Dialog verify
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Bạn có chắc chọn đáp án " + v.getId() + " không?");
        b.setMessage("Suy nghĩ thật kĩ nhé!");
        b.setPositiveButton("Không chọn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(HienThiCauHoi.this, "Thời gian vẫn đang chạy đấy nhé!", Toast.LENGTH_SHORT).show();
            }
        });
        b.setNegativeButton("Vẫn chọn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Xử lý đúng sai
                if (String.valueOf(v.getId()) == lst_cauhoi.get(number.get(pos)).DapAn) {
                    point = point + 1;
                }

                //Load lại time
                loadTime.cancel(true);
                loadTime = new LoadTime(HienThiCauHoi.this);
                loadTime.execute();

                //Chuyển câu hỏi
                pos++;
                if (pos >= lst_cauhoi.size()) pos = lst_cauhoi.size() - 1;
                ShowQuestion(pos);
            }
        });
        b.create().show();
    }
}