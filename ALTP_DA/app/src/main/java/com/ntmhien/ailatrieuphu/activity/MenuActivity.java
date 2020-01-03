package com.ntmhien.ailatrieuphu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.ntmhien.ailatrieuphu.api.GetAPILinhVuc;
import com.ntmhien.ailatrieuphu.R;

import java.util.concurrent.ExecutionException;

public class MenuActivity extends AppCompatActivity {
    ImageView imageView;
    EditText name,email,id;
    TextView ten;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        ten =  findViewById(R.id.txtUser);
        imageView = findViewById(R.id.imgvat);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
            ten.setText(personName);


        }
    }

    public void btnNewGame(View v) throws ExecutionException, InterruptedException {
        Button btn = (Button) v;
        String lst = new GetAPILinhVuc(this).execute().get();
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