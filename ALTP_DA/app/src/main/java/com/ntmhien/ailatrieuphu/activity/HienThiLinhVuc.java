package com.ntmhien.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;
import com.ntmhien.ailatrieuphu.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.ntmhien.ailatrieuphu.model.LinhVuc;

import java.util.ArrayList;

public class HienThiLinhVuc extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private ImageView Prof;
    private ArrayList<LinhVuc> lst_linhvuc;
    private ToggleButton togMusic;
    private ToggleButton togSound;

    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc);

        initView();
    }
    private void initView() {
        drawerLayout = (DrawerLayout)findViewById(R.id.dlmain);
        togMusic = ((ToggleButton)findViewById(R.id.tog_music));
        togSound = ((ToggleButton)findViewById(R.id.tog_sound));
        Prof = (ImageView)findViewById(R.id.imgProf);

        Prof.setOnClickListener(this);
        togMusic.setOnClickListener(this);
        togSound.setOnClickListener(this);
    }

    private void setLinhVuc() {
        Intent intent = getIntent();
        final String jSonString = intent.getStringExtra("message");

        if (get_lst_linhvuc(jSonString) == true) {
            m_txt_num.setText("Câu: 1");
            m_txt_content.setText(lst_cauhoi.get(number.get(pos)).NoiDung);
            txt_DA[0].setText("A. " + lst_cauhoi.get(number.get(pos)).PhuongAn1);
        } else {
            m_txt_content.setText("API không hoạt động.");
            m_txt_num.setVisibility(View.INVISIBLE);
            txt_DA[0].setVisibility(View.INVISIBLE);
        }
    }

    public void ShowLinhVuc(int pos) {
        txt_DA[0].setBackgroundResource(R.drawable.answer_background_normal);
        txt_DA[1].setBackgroundResource(R.drawable.answer_background_normal);
    }

    private Boolean get_lst_linhvuc(String jSonString) {
        try {
            lst_linhvuc = new ArrayList();
            JSONArray jr = new JSONArray(jSonString);
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject jb = (JSONObject) jr.getJSONObject(i);
                LinhVuc quiz = new LinhVuc();
                quiz.TenLinhVuc = jb.getString("ten_linh_vuc");
                lst_linhvuc.add(quiz);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgProf:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.tog_music:
                if(togMusic.isChecked()){
                    togMusic.setBackgroundResource(R.drawable.toggle_button_on);
                    togMusic.setChecked(true);
                }else{
                    togMusic.setBackgroundResource(R.drawable.toggle_button_off);
                    togMusic.setChecked(false);
                }
                break;
            case R.id.tog_sound:
                if(togSound.isChecked()){
                    togSound.setBackgroundResource(R.drawable.toggle_button_on);
                    togSound.setChecked(true);
                }else{
                    togSound.setBackgroundResource(R.drawable.toggle_button_off);
                    togSound.setChecked(false);
                }
                break;
            default:
                break;
        }
    }
}
