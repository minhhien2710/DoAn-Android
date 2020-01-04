package com.ntmhien.ailatrieuphu.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.ntmhien.ailatrieuphu.R;
import com.ntmhien.ailatrieuphu.model.GoiCredit;
import com.ntmhien.ailatrieuphu.model.LinhVuc;
import com.ntmhien.ailatrieuphu.music.MusicManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MuaCredit extends AppCompatActivity {
    private ArrayList<GoiCredit> mCredit;
    private MusicManager musicManager;
    private DrawerLayout drawerLayout;
    private ImageView Prof;
    private ArrayList<LinhVuc> lst_linhvuc;
    private ToggleButton togMusic;
    private ToggleButton togSound;
    private String jSonGoiCredit;
    private TextView tenGoiA, tenGoiB, tenGoiC, tenGoiD, giaTienA, giaTienB, giaTienC, giaTienD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_credit);

        tenGoiA = findViewById(R.id.txtGoiA);
        tenGoiB = findViewById(R.id.txtGoiB);
        tenGoiC = findViewById(R.id.txtGoiC);
        tenGoiD = findViewById(R.id.txtGoiD);
        giaTienA = findViewById(R.id.txtGiaTienA);
        giaTienB = findViewById(R.id.txtGiaTienB);
        giaTienC = findViewById(R.id.txtGiaTienC);
        giaTienD = findViewById(R.id.txtGiaTienD);


        Intent intent = getIntent();
        jSonGoiCredit = intent.getStringExtra("DanhSachGoiCredit");

//        Toast.makeText(this, jSonGoiCredit, Toast.LENGTH_LONG).show();

        HienThiGoiCredit();
    }

    @SuppressLint("SetTextI18n")
    public void HienThiGoiCredit() {

        if (getListGoiCredit(jSonGoiCredit)) {
            tenGoiA.setText(mCredit.get(0).getTenGoi());
            tenGoiB.setText(mCredit.get(1).getTenGoi());
            tenGoiC.setText(mCredit.get(2).getTenGoi());
            tenGoiD.setText(mCredit.get(3).getTenGoi());

            giaTienA.setText("$" + mCredit.get(0).getGiaTien());
            giaTienB.setText("$" + mCredit.get(1).getGiaTien());
            giaTienC.setText("$" + mCredit.get(2).getGiaTien());
            giaTienD.setText("$" + mCredit.get(3).getGiaTien());
        } else {
            tenGoiA.setText("API not run");
            tenGoiB.setVisibility(View.INVISIBLE);
            tenGoiC.setVisibility(View.INVISIBLE);
            tenGoiD.setVisibility(View.INVISIBLE);
            giaTienA.setText("API not run");
            giaTienB.setVisibility(View.INVISIBLE);
            giaTienC.setVisibility(View.INVISIBLE);
            giaTienD.setVisibility(View.INVISIBLE);
        }
    }

    public Boolean getListGoiCredit(String jSonString) {
        try {
            mCredit = new ArrayList<>();
            JSONObject root = new JSONObject(jSonString);
            JSONArray jr = root.getJSONArray("data");
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject jb = jr.getJSONObject(i);
                GoiCredit thongTin = new GoiCredit();
                thongTin.setTenGoi(jb.getString("ten_goi"));
                thongTin.setGiaTien(jb.getString("so_tien"));
                mCredit.add(thongTin);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void Back(View view) {
        this.finish();
    }


}
