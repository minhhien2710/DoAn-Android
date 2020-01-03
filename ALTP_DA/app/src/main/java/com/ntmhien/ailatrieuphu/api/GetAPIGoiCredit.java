package com.ntmhien.ailatrieuphu.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.ntmhien.ailatrieuphu.activity.MuaCredit;

public class GetAPIGoiCredit extends AsyncTask<String, String, String> {
    private Context m_con;
    public GetAPIGoiCredit(Context con) {
        m_con = con;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent = new Intent(m_con, MuaCredit.class);
        intent.putExtra("message", s);
        Activity activity = (Activity) m_con;
        activity.startActivity(intent);
    }
    @Override
    protected String doInBackground(String... level) {
        return APIGoiCredit.getAPIGoiCredit();
    }
}
