package com.ntmhien.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutionException;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void btnNewGame(View v) throws ExecutionException, InterruptedException {
        Button btn = (Button) v;
        String lst = new GetAPICauHoi(this).execute(v.getId() == R.id.btnNewGame ? "1" : "2").get();
    }
}
