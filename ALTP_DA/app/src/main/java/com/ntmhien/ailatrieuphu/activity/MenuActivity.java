package com.ntmhien.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ntmhien.ailatrieuphu.api.GetAPICauHoi;
import com.ntmhien.ailatrieuphu.api.GetAPILinhVuc;
import com.ntmhien.ailatrieuphu.music.MusicManager;
import com.ntmhien.ailatrieuphu.R;

import java.util.concurrent.ExecutionException;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void btnNewGame(View v) throws ExecutionException, InterruptedException {
        Button btn = (Button) v;
        String lst = new GetAPILinhVuc(this).execute().get();
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnProfile:
                Intent intent = new Intent(MenuActivity.this, LinhVuc.class);
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