package com.ntmhien.ailatrieuphu.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ntmhien.ailatrieuphu.activity.MenuActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class GetAPINguoiCHoi extends AsyncTask<String, String, String> {
    private Context m_con;

    public GetAPINguoiCHoi(Context con)
    {
        m_con = con;
    }

    @Override
    protected String doInBackground(String... link) {
        return APINguoiChoi.getNguoiChoi(link[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s==null){
            Toast.makeText(m_con, "Đăng nhập thất bại! Vui lòng kiểm tra lại thông tin tài khoản", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONObject data = new JSONObject(s);
            boolean status = data.getBoolean("status");

            if(status)
            {
                Intent intent = new Intent(m_con, MenuActivity.class);
                intent.putExtra("token", s);

                Activity activity = (Activity) m_con;
                activity.startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}