package com.ntmhien.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.ntmhien.ailatrieuphu.R;

public class LinhVuc extends AppCompatActivity implements View.OnClickListener {
private DrawerLayout drawerLayout;

private ImageView Prof;
    private ToggleButton togMusic;
    private ToggleButton togSound;
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
