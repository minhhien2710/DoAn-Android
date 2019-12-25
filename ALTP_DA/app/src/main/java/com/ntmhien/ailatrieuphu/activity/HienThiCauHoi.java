package com.ntmhien.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ntmhien.ailatrieuphu.dialogs.GoiChoNguoiThan;
import com.ntmhien.ailatrieuphu.dialogs.TroGiupKhangGia;
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
    private int sttcau = 1;
    private TextView m_txt_num;
    private TextView m_txt_content;
    private TextView m_Point;
    private TextView txt_DA[];
    private ImageView btnCall, btnAudience, btnCredit, btn5050, btnChange;
    private DrawerLayout dl;
    private ImageView Prof;
    private TroGiupKhangGia troGiupKhangGia;
    private GoiChoNguoiThan goiChoNguoiThan;


    List<Integer> number = new ArrayList<Integer>();

    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cauhoi);

        txt_DA = new TextView[4];

        goiChoNguoiThan = new GoiChoNguoiThan(this);
        troGiupKhangGia = new TroGiupKhangGia(this);

        setLoadTime();
        findViewByIds();
        setEvents();
        setCauHoi();
    }

    private void findViewByIds() {
        m_txt_num = (TextView) findViewById(R.id.txtSoCau);
        m_txt_content = (TextView) findViewById(R.id.txtCauHoi);
        txt_DA[0] = findViewById(R.id.A);
        txt_DA[1] = findViewById(R.id.B);
        txt_DA[2] = findViewById(R.id.C);
        txt_DA[3] = findViewById(R.id.D);

        m_Point = findViewById(R.id.txtPoint);

        progressBar = findViewById(R.id.timeProgressBar);

        dl = findViewById(R.id.dlcauhoi);
        Prof = findViewById(R.id.imgAvatar);

        btn5050 = (ImageView) findViewById(R.id.btn50_50);
        btnAudience = (ImageView) findViewById(R.id.btnAudience);
        btnChange = (ImageView) findViewById(R.id.btnChuyencau);
        btnCall = (ImageView) findViewById(R.id.btnCall);
        btnCredit = (ImageView) findViewById(R.id.btnCredit);
    }

    private void setEvents() {
        txt_DA[0].setOnClickListener(this);
        txt_DA[1].setOnClickListener(this);
        txt_DA[2].setOnClickListener(this);
        txt_DA[3].setOnClickListener(this);

        btnCredit.setOnClickListener(this);
        btnAudience.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btn5050.setOnClickListener(this);

        Prof.setOnClickListener(this);
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

                musicManager= new MusicManager();
                musicManager.setNhacNenKhiDangTraLoi(HienThiCauHoi.this);

                if (get_lst_cauhoi(jSonString) == true) {
                    m_txt_num.setText("Câu: 1");
                    m_txt_content.setText(lst_cauhoi.get(number.get(pos)).NoiDung);
                    txt_DA[0].setText("A. " + lst_cauhoi.get(number.get(pos)).PhuongAn1);
                    txt_DA[1].setText("B. " + lst_cauhoi.get(number.get(pos)).PhuongAn2);
                    txt_DA[2].setText("C. " + lst_cauhoi.get(number.get(pos)).PhuongAn3);
                    txt_DA[3].setText("D. " + lst_cauhoi.get(number.get(pos)).PhuongAn4);
                } else {
                    m_txt_content.setText("API không hoạt động.");
                    m_txt_num.setVisibility(View.INVISIBLE);
                    txt_DA[0].setVisibility(View.INVISIBLE);
                    txt_DA[1].setVisibility(View.INVISIBLE);
                    txt_DA[2].setVisibility(View.INVISIBLE);
                    txt_DA[3].setVisibility(View.INVISIBLE);
                }
            }
        }, 6000);
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
        }, 5300);
    }

    public void ShowQuestion(int pos) {
        //Setup lại
        loadTime.cancel(true);
        loadTime = new LoadTime(HienThiCauHoi.this);
        loadTime.execute();

        txt_DA[0].setBackgroundResource(R.drawable.answer_background_normal);
        txt_DA[1].setBackgroundResource(R.drawable.answer_background_normal);
        txt_DA[2].setBackgroundResource(R.drawable.answer_background_normal);
        txt_DA[3].setBackgroundResource(R.drawable.answer_background_normal);

        progressBar.setVisibility(View.VISIBLE);
        setClickAble(true);

        //Chuyển câu
        m_txt_num.setText("Câu: " + sttcau);
        m_txt_content.setText(lst_cauhoi.get(number.get(pos)).NoiDung);
        txt_DA[0].setText("A. " + lst_cauhoi.get(number.get(pos)).PhuongAn1);
        txt_DA[1].setText("B. " + lst_cauhoi.get(number.get(pos)).PhuongAn2);
        txt_DA[2].setText("C. " + lst_cauhoi.get(number.get(pos)).PhuongAn3);
        txt_DA[3].setText("D. " + lst_cauhoi.get(number.get(pos)).PhuongAn4);
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
            for (int i = 0; i < num; i++) {
                while (true) {
                    Integer next = rd.nextInt(num);
                    if (!number.contains(next)) {
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

    private void xuLyDungSai(final View v, final String DapAn) {
        setClickAble(false);

        musicManager = new MusicManager();
        handler = new Handler();

        progressBar.setVisibility(View.GONE);
        loadTime.cancel(true);
        v.setBackgroundResource(R.drawable.player_answer_background_selected);

        musicManager.setNhacChonDapAn(this, DapAn);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                musicManager.setNhacChuanBiDocDapAn(HienThiCauHoi.this);
                if (DapAn.equals(lst_cauhoi.get(number.get(pos)).DapAn)) {
                    traLoiDung(v, DapAn);
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
                musicManager.setNhacChonDapAnDung(HienThiCauHoi.this, DapAn);
                v.setBackgroundResource(R.drawable.player_answer_background_true);
                //Cộng điểm
                point = point + 1000;
                m_Point.setText("Điểm: " + point);
            }
        }, 3500);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Music
                musicManager.setNhacCauHoiTiepTheo(HienThiCauHoi.this);
                //Câu kế tiếp
                pos++;
                sttcau++;
                if (pos >= lst_cauhoi.size()) pos = lst_cauhoi.size() - 1;
                ShowQuestion(pos);
            }
        }, 8000);
    }

    private void setClickAble(boolean b) {
        txt_DA[0].setClickable(b);
        txt_DA[1].setClickable(b);
        txt_DA[2].setClickable(b);
        txt_DA[3].setClickable(b);

        btnCredit.setClickable(b);
        btnAudience.setClickable(b);
        btnCall.setClickable(b);
        btnChange.setClickable(b);
        btn5050.setClickable(b);
    }
public void TiepTucChoi(){
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setTitle("Thông báo");
        b.setIcon(R.drawable.profile_icon_money);
        b.setMessage("Bạn có muốn sử dụng 100 Credit để tiếp tục: ");
        b.setPositiveButton("Sử dụng Credit", new DialogInterface. OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                point = point + 1000;
                m_Point.setText("Điểm: " + point);
                pos++;
                sttcau++;
                if (pos >= lst_cauhoi.size()) pos = lst_cauhoi.size() - 1;
                ShowQuestion(pos);
            }});
        b.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HienThiCauHoi.this.finish();
            }
        });
        b.create().show();
    }
    @Override
    public void onClick(final View v) {
        musicManager = new MusicManager();
        //Chuyển ABCD thành 1234
        String DA = lst_cauhoi.get(number.get(pos)).DapAn;
        int iDA = (DA.equals("A") ? 1 : DA.equals("B") ? 2 : DA.equals("C") ? 3 : 4);

        switch (v.getId()) {
            case R.id.A:
                xuLyDungSai(v, "A");
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
            case R.id.btnCredit:
                TiepTucChoi();
                break;
            case R.id.btn50_50:
                btn5050.setEnabled(false);
                Random random = new Random();
                int count = 0;
                int b = 0;

                while (count < 2) {
                    int temp = random.nextInt(4) + 1;
                    if (temp != iDA && temp != b) {
                        b = temp;
                        txt_DA[b - 1].setEnabled(false);
                        txt_DA[b - 1].setBackgroundResource(R.drawable.answer_background_hide);
                        txt_DA[b - 1].setText("");
                        count++;
                    }
                }
                break;
            case R.id.btnChuyencau:
                btnChange.setEnabled(false);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Music
                        musicManager.setNhacCauHoiTiepTheo(HienThiCauHoi.this);
                        //Câu kế tiếp
                        pos++;
                        if (pos >= lst_cauhoi.size()) pos = lst_cauhoi.size() - 1;
                        ShowQuestion(pos);
                    }
                }, 0);
                break;
            case R.id.btnCall:
                btnCall.setEnabled(false);
                //Xử lý tỉ lệ đúng 90%
                Random rd = new Random();
                int tile = rd.nextInt(10) + 1;
                if ( tile == 3 ) {
                    while (true){
                        int n = rd.nextInt(4) + 1;
                        if ( n != iDA ) {
                            goiChoNguoiThan.setTrueAnswer(n);
                            break;
                        }
                    }
                } else {
                    goiChoNguoiThan.setTrueAnswer(iDA);
                }
                goiChoNguoiThan.show();

                break;
            case R.id.btnAudience:
                btnAudience.setEnabled(false);

                String cs = "";
                for (int i = 0; i < txt_DA.length; i++) {
                    if (!txt_DA[i].isEnabled()) {
                        cs += i;
                    }
                }
                //Xử lý tỉ lệ đúng 90%
                Random rd2 = new Random();
                int tile2 = rd2.nextInt(10) + 1;
                if ( tile2 == 3 ) {
                    while (true){
                        int n2 = rd2.nextInt(4) + 1;
                        if ( n2 != iDA ) {
                            troGiupKhangGia.prepareVote(n2, cs);
                            break;
                        }
                    }
                } else {
                    troGiupKhangGia.prepareVote(iDA, cs);
                }
                troGiupKhangGia.show();
                troGiupKhangGia.voteAnswer();
                break;
            default:
                break;

        }
    }
}