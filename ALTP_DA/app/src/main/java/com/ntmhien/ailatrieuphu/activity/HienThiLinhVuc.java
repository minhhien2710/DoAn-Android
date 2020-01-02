package com.ntmhien.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
    private Button btn_lv[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc);

        btn_lv = new Button[4];
        btn_lv[0] = findViewById(R.id.button1);
        btn_lv[1] = findViewById(R.id.button2);
        btn_lv[2] = findViewById(R.id.button3);
        btn_lv[3] = findViewById(R.id.button4);

        initView();
        setLinhVuc();
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
            for(int i=0;i<jSonString.length();i++){
                btn_lv[i].setText(lst_linhvuc.get(i).TenLinhVuc);
            }
        } else {
            btn_lv[0].setVisibility(View.INVISIBLE);
            btn_lv[1].setVisibility(View.INVISIBLE);
            btn_lv[2].setVisibility(View.INVISIBLE);
            btn_lv[3].setVisibility(View.INVISIBLE);
        }
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
