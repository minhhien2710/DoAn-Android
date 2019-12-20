package com.ntmhien.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ntmhien.ailatrieuphu.model.CauHoi;
import com.ntmhien.ailatrieuphu.fragments.LoadTime;
import com.ntmhien.ailatrieuphu.R;
import com.ntmhien.ailatrieuphu.music.MusicManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HienThiCauHoi extends AppCompatActivity implements View.OnClickListener {
    private MusicManager musicManager;
    private Handler handler;
    private ProgressBar progressBar;
    private LoadTime loadTime;
    private ArrayList<CauHoi> lst_cauhoi;
    private int point = 0;
    private TextView m_txt_num;
    private TextView m_txt_content;
    private TextView m_DA1, m_DA2, m_DA3, m_DA4, m_Point;

    private DrawerLayout dl;
    private ImageView Prof;

    List<Integer> number = new ArrayList<Integer>();

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
        setClickAble(false);
        progressBar.setVisibility(View.GONE);
        handler = new Handler();

        Intent intent = getIntent();
        final String jSonString = intent.getStringExtra("message");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setClickAble(true);
                progressBar.setVisibility(View.VISIBLE);

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
        },6000);
    }

    private void setLoadTime() {
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadTime == null) {
                    loadTime = new LoadTime(HienThiCauHoi.this);
                    loadTime.execute();
                } else if (loadTime.getStatus() == LoadTime.Status.FINISHED) {
                    loadTime = new LoadTime(HienThiCauHoi.this);
                    loadTime.execute();
                }
            }
        },5300);
    }

    private void findViewByIds() {
        m_txt_num = (TextView) findViewById(R.id.txtSoCau);
        m_txt_content = (TextView) findViewById(R.id.txtCauHoi);
        m_DA1 = findViewById(R.id.A);
        m_DA2 = findViewById(R.id.B);
        m_DA3 = findViewById(R.id.C);
        m_DA4 = findViewById(R.id.D);

        m_Point=findViewById(R.id.txtPoint);

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
            Random rd = new Random();
            for (int i = 0; i < num; i++)
            {
                while(true)
                {
                    Integer next = rd.nextInt(num);
                    if (!number.contains(next))
                    {
                        number.add(next);
                        break;
                    }
                }
            }
            //---//
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

    private void xuLyDungSai(final View v, final String DapAn){
        setClickAble(false);

        musicManager = new MusicManager();
        handler = new Handler();

        progressBar.setVisibility(View.GONE);
        loadTime.cancel(true);
        v.setBackgroundResource(R.drawable.player_answer_background_selected);

        musicManager.setNhacChonDapAn(this,DapAn);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                musicManager.setNhacChuanBiDocDapAn(HienThiCauHoi.this);
                if (DapAn.equals(lst_cauhoi.get(number.get(pos)).DapAn)) {
                    traLoiDung(v,DapAn);
                }
            }
        }, 3500);
    }

    private void traLoiDung(final View v, final String DapAn) {
        musicManager = new MusicManager();
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                musicManager.setNhacChonDapAnDung(HienThiCauHoi.this,DapAn);
                v.setBackgroundResource(R.drawable.player_answer_background_true);
            }
        },3500);

        //Cộng điểm
        point = point + 1000;
        m_Point.setText("Điểm: " + point);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //setup lại
                loadTime.cancel(true);
                loadTime = new LoadTime(HienThiCauHoi.this);
                loadTime.execute();
                v.setBackgroundResource(R.drawable.level_click_bg);
                progressBar.setVisibility(View.VISIBLE);
                setClickAble(true);
                //Music
                musicManager.setNhacCauHoiTiepTheo(HienThiCauHoi.this);
                //Câu kế tiếp
                pos++;
                if (pos >= lst_cauhoi.size()) pos = lst_cauhoi.size() - 1;
                ShowQuestion(pos);
            }
        },8000);
    }

    private void setClickAble(boolean b) {
        m_DA1.setClickable(b);
        m_DA2.setClickable(b);
        m_DA3.setClickable(b);
        m_DA4.setClickable(b);
    }

    @Override
    public void onClick(final View v) {
        musicManager = new MusicManager();
        switch (v.getId()) {
            case R.id.A:
                xuLyDungSai(v,"A");
                break;
            case R.id.B:
                xuLyDungSai(v, "B");
                break;
            case R.id.C:
                xuLyDungSai(v, "C");
                break;
            case R.id.D:
                xuLyDungSai(v, "D");
                break;
            default:
                break;
        }
    }
}