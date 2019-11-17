package com.ntmhien.ailatrieuphu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class QuanLiCauHoi implements Iterator<CauHoi> {
    LinkedList<CauHoi> dsCauHoi;
    int posI;

    private QuanLiCauHoi(JSONArray job){
        dsCauHoi = new LinkedList<>();
        try {
            for(int i=0;i<job.length();i++){
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject e = job.getJSONObject(i);
                dsCauHoi.add(CauHoi.ParseJSON(e));
            }
        } catch (JSONException e) {
        }
        reset();
    }

    public static QuanLiCauHoi CreateByJSON(String json) throws JSONException {
        return new QuanLiCauHoi(new JSONArray(json));
    }

    @Override
    public boolean hasNext() {
        return posI < dsCauHoi.size();
    }

    @Override
    public CauHoi next() {
        return dsCauHoi.get(posI++);
    }

    public void reset(){
        posI = 0;
    }
}