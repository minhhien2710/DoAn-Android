package com.ntmhien.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ntmhien.ailatrieuphu.R;
import com.ntmhien.ailatrieuphu.api.BitMapBase64;
import com.ntmhien.ailatrieuphu.api.MD5_String;
import com.ntmhien.ailatrieuphu.api.PostAPI;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class DangKi extends AppCompatActivity {

    private EditText m1,m2,m3,m4;
    private Button button;
    private ImageView hinh;
    private Bitmap bitmap;
    private  String ten_đang_nhap,email,mat_khau,xn_mat_khau;
    private String duongdan="http://10.0.2.2:8000/api/nguoi-choi/them-nguoi-choi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        m1=findViewById(R.id.txtTK);
        m2 = findViewById(R.id.txtEmail);
        m3 = findViewById(R.id.txtMK);
        m4=findViewById(R.id.txtNMK);
        hinh=findViewById(R.id.imageView2);


    }
    public void DangKi(View view) throws NoSuchAlgorithmException {

        ten_đang_nhap=m1.getText().toString();
        email=m2.getText().toString();
        mat_khau= m3.getText().toString();
        xn_mat_khau=m4.getText().toString();
        bitmap = ((BitmapDrawable) hinh.getDrawable()).getBitmap();
        if(mat_khau.equals(xn_mat_khau)){


            Map<String,String> NguoiChoi= new HashMap<>();
            NguoiChoi.put("ten_dang_nhap",ten_đang_nhap);
            NguoiChoi.put("mat_khau", MD5_String.convertHashToStringMD5(mat_khau));
            NguoiChoi.put("email",email);
            NguoiChoi.put("anh_dai_dien", BitMapBase64.encodeBitmapToString(bitmap));
            NguoiChoi.put("diem_cao_nhat","0");
            NguoiChoi.put("credit","50");
            PostAPI.PostAPI(this,NguoiChoi,duongdan);
        }
        else {
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
        }

    }
}
