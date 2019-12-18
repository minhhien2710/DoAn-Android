package com.ntmhien.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ntmhien.ailatrieuphu.music.MusicManager;
import com.ntmhien.ailatrieuphu.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonDangNhap;
    MusicManager musicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonDangNhap = (Button) findViewById(R.id.btnDangNhap);
        buttonDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        musicManager = new MusicManager();
        musicManager.setNhacNen(MainActivity.this);
    }
}