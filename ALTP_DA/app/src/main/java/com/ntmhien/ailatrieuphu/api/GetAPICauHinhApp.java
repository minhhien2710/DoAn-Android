package com.ntmhien.ailatrieuphu.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.ntmhien.ailatrieuphu.activity.MenuActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class GetAPICauHinhApp extends AsyncTask<String, String, String> {
    private Context m_con;
    public GetAPICauHinhApp(Context con) {
        m_con = con;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject data = new JSONObject(s);
            boolean status = data.getBoolean("status");

            if(status)
            {
                Intent intent = new Intent(m_con, MenuActivity.class);
                intent.putExtra("data", s);

                Activity activity = (Activity) m_con;
                activity.startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected String doInBackground(String... level) {
        return APICauHinhApp.getCauHinhApp();
    }
}
