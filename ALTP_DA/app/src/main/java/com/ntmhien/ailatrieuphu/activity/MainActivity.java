package com.ntmhien.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.ntmhien.ailatrieuphu.api.GetAPICauHinhApp;
import com.ntmhien.ailatrieuphu.api.GetAPINguoiCHoi;
import com.ntmhien.ailatrieuphu.music.MusicManager;
import com.ntmhien.ailatrieuphu.R;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonDangNhap;
    private TextView tk,mk;
    MusicManager musicManager;
    SignInButton sigin;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicManager = new MusicManager();
        musicManager.setNhacNen(MainActivity.this);

        buttonDangNhap = (Button) findViewById(R.id.btnDangNhap);
        buttonDangNhap.setOnClickListener(this);

        sigin = findViewById(R.id.sign_in_button);
        sigin.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        tk=findViewById(R.id.txtTK);
        mk=findViewById(R.id.txtMK);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.btnDangNhap:
                String link = "http://10.0.2.2:8000/api/dang-nhap?ten_dang_nhap="+tk.getText()+"&mat_khau="+mk.getText();
                try {
                    String lst = new GetAPINguoiCHoi(MainActivity.this).execute(link).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnDangky:
                Intent intent2 = new Intent(MainActivity.this, DangKi.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

            // Signed in successfully, show authenticated UI.
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.v("Erro", "signInResult:failed code=" + e.getStatusCode());
        }
    }

}