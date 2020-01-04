package com.ntmhien.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.ntmhien.ailatrieuphu.dialogs.GoiChoNguoiThan;
import com.ntmhien.ailatrieuphu.dialogs.TroGiupKhangGia;
import com.ntmhien.ailatrieuphu.model.CauHinhApp;
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
    private TextView tentk;
    private ImageView btnCall, btnAudience, btnCredit, btn5050, btnChange;
    private DrawerLayout dl;
    private ImageView Prof;
    private TroGiupKhangGia troGiupKhangGia;
    private GoiChoNguoiThan goiChoNguoiThan;

    List<Integer> number = new ArrayList<Integer>();

    public int pos = 0;

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

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            Uri personPhoto = acct.getPhotoUrl();

            Glide.with(this).load(String.valueOf(personPhoto)).into(Prof);
            tentk.setText(personName);
        }
    }

    private void findViewByIds() {
        m_txt_num = (TextView) findViewById(R.id.txtSoCau);
        m_txt_content = (TextView) findViewById(R.id.txtCauHoi);
        txt_DA[0] = findViewById(R.id.A);
        txt_DA[1] = findViewById(R.id.B);
        txt_DA[2] = findViewById(R.id.C);
        txt_DA[3] = findViewById(R.id.D);

        m_Point = findViewById(R.id.txtPoint);
        tentk = findViewById(R.id.txtName);

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
            JSONObject data = new JSONObject(jSonString);
            JSONArray jr = data.getJSONArray("data");
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject jb = (JSONObject) jr.getJSONObject(i);
                CauHoi quiz = new CauHoi();
                quiz.NoiDung = jb.getString("noi_dung");
                quiz.PhuongAn1 = jb.getString("phuong_an_a");
                quiz.PhuongAn2 = jb.getString("phuong_an_b");
                quiz.PhuongAn3 = jb.getString("phuong_an_c");
                quiz.PhuongAn4 = jb.getString("phuong_an_d");
                quiz.DapAn = jb.getString("dap_an");
                quiz.DoKho = jb.getString("do_kho");
                quiz.Chon = "0";
                lst_cauhoi.add(quiz);
            }

            //Random câu hỏi và chia độ khó
            Random rd = new Random();
            for (int i = 0; i < num; i++) {
                if (i<5){
                    while (true) {
                        Integer next = rd.nextInt(num);
                        if (!number.contains(next) && (lst_cauhoi.get(next).DoKho).equals("1")){
                            number.add(next);
                            break;
                        }
                    }
                }
                else {
                    while (true) {
                        Integer next = rd.nextInt(num);
                        if(!number.contains(next) && (lst_cauhoi.get(next).DoKho).equals("1")){
                            i++;
                            break;
                        }else if(!number.contains(next) && (lst_cauhoi.get(next).DoKho).equals("2")){
                            number.add(next);
                            break;
                        }
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
                } else traLoiSai(v, lst_cauhoi.get(number.get(pos)).DapAn);
            }
        }, 3500);
    }

    private void traLoiSai(final View v, final String DapAn) {
        musicManager = new MusicManager();
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                musicManager.setNhacDapAnDungLa(HienThiCauHoi.this, DapAn);
                v.setBackgroundResource(R.drawable.play_answer_background_wrong);
                txt_DA[getDapAnDung()-1].setBackgroundResource(R.drawable.play_answer_background_true);
                txt_DA[getDapAnDung()-1].startAnimation(AnimationUtils.loadAnimation(HienThiCauHoi.this,R.anim.fade_loop));
            }
        }, 3500);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyTiepTucChoi();
            }
        }, 8000);
    }

    private void traLoiDung(final View v, final String DapAn) {
        musicManager = new MusicManager();
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                musicManager.setNhacChonDapAnDung(HienThiCauHoi.this, DapAn);
                v.setBackgroundResource(R.drawable.play_answer_background_true);
                v.startAnimation(AnimationUtils.loadAnimation(HienThiCauHoi.this,R.anim.fade_loop));

                updatePoint();
            }
        }, 3500);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Music
                musicManager.setNhacCauHoiTiepTheo(HienThiCauHoi.this);
                //Câu kế tiếp
                setCauTiepTheo();
            }
        }, 8000);
    }

    public void notifyTiepTucChoi(){
        musicManager = new MusicManager();

        Toast.makeText(HienThiCauHoi.this, "Bạn đã trả lời sai !", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Tổng điểm của bạn là: "+point);
        b.setIcon(R.drawable.profile_icon_money);
        b.setMessage("Bạn có muốn sử dụng 500 Credit để chơi tiếp hay không? ");
        b.setNegativeButton("Sử dụng Credit", new DialogInterface. OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                updatePoint();
                musicManager.setNhacCauHoiTiepTheo(HienThiCauHoi.this);
                //Câu kế tiếp
                setCauTiepTheo();
            }});
        b.setPositiveButton("Dừng cuộc chơi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                musicManager.setNhacThuaCuoc(HienThiCauHoi.this);
                HienThiCauHoi.this.finish();
            }
        });
        b.create().show();
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

    public void updatePoint(){
        point = point + sttcau*1000;
        m_Point.setText("Điểm: " + point);
    }

    public void setCauTiepTheo(){
        pos++;
        sttcau++;
        if (pos >= lst_cauhoi.size()) pos = lst_cauhoi.size() - 1;
        ShowQuestion(pos);
    }

    private int getDapAnDung(){
        String DA = lst_cauhoi.get(number.get(pos)).DapAn;
        int iDA = (DA.equals("A") ? 1 : DA.equals("B") ? 2 : DA.equals("C") ? 3 : 4);
        return iDA;
    }

    public void stopGame() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Bạn có thật sự muốn thoát?");
        b.setMessage("Bạn sẽ không được lưu điểm nếu thoát giữa chừng. ");
        b.setNegativeButton("Thoát", new DialogInterface. OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                musicManager.setNhacThuaCuoc(HienThiCauHoi.this);
                loadTime.cancel(true);
                HienThiCauHoi.this.finish();
            }});
        b.setPositiveButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        b.create().show();
    }

    @Override
    public void onBackPressed() {
        if (Thread.currentThread().isAlive()) {
            stopGame();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(final View v) {
        musicManager = new MusicManager();

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
                break;
            case R.id.btn50_50:
                handler = new Handler();
                btn5050.setEnabled(false);
                musicManager.setNhac5050(HienThiCauHoi.this);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Random random = new Random();
                        int count = 0;
                        int b = 0;

                        while (count < 2) {
                            int temp = random.nextInt(4) + 1;
                            if (temp != getDapAnDung() && temp != b) {
                                b = temp;
                                txt_DA[b - 1].setEnabled(false);
                                txt_DA[b - 1].setBackgroundResource(R.drawable.answer_background_hide);
                                txt_DA[b - 1].setText("");
                                count++;
                            }
                        }
                    }
                }, 2700);
                break;
            case R.id.btnChuyencau:
                btnChange.setEnabled(false);
                //Music
                musicManager.setNhacCauHoiTiepTheo(HienThiCauHoi.this);
                //Câu kế tiếp
                sttcau--;
                setCauTiepTheo();
                break;
            case R.id.btnCall:
                btnCall.setEnabled(false);

                //Xử lý tỉ lệ đúng 90%
                Random rd = new Random();
                int tile = rd.nextInt(10) + 1;
                if ( tile == 3 ) {
                    while (true){
                        int n = rd.nextInt(4) + 1;
                        if ( n != getDapAnDung() ) {
                            goiChoNguoiThan.setTrueAnswer(n);
                            break;
                        }
                    }
                } else {
                    goiChoNguoiThan.setTrueAnswer(getDapAnDung());
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
                        if ( n2 != getDapAnDung() ) {
                            troGiupKhangGia.prepareVote(n2, cs);
                            break;
                        }
                    }
                } else {
                    troGiupKhangGia.prepareVote(getDapAnDung(), cs);
                }
                troGiupKhangGia.show();
                troGiupKhangGia.voteAnswer();

                break;
            default:
                break;
        }
    }
}