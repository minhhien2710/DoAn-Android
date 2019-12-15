package com.ntmhien.ailatrieuphu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class GetAPICauHoi extends AsyncTask<String, String, String> {
    private Context m_con;
    public GetAPICauHoi(Context con) {
        m_con = con;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent = new Intent(m_con, HienThiCauHoi.class);
        intent.putExtra("message", s);
        Activity activity = (Activity) m_con;
        activity.startActivity(intent);
    }
    @Override
    protected String doInBackground(String... level) {
        return APICauHoi.getQuestions(level[0]);
    }
}