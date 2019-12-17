package com.ntmhien.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.concurrent.ExecutionException;

public class MenuActivity extends AppCompatActivity {

    MusicManager musicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void btnNewGame(View v) throws ExecutionException, InterruptedException {
        Button btn = (Button) v;
        String lst = new GetAPICauHoi(this).execute(v.getId() == R.id.btnNewGame ? "1" : "2").get();

        musicManager = new MusicManager();
        musicManager.setNhacBatDauGame(MenuActivity.this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnProfile:
                Intent intent = new Intent(MenuActivity.this, QL_TaiKhoan.class);
                startActivity(intent);
                break;
            case R.id.btnHistory:
                Intent intent1 = new Intent(MenuActivity.this, LichSuChoi.class);
                startActivity(intent1);
                break;
            case R.id.btnBXH:
                Intent intent2 = new Intent(MenuActivity.this, BXH.class);
                startActivity(intent2);
            case R.id.btnBuyCredit:
                Intent intent3 = new Intent(MenuActivity.this, MuaCredit.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}