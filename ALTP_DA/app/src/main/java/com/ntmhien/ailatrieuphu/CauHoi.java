package com.ntmhien.ailatrieuphu;

import org.json.JSONException;
import org.json.JSONObject;

public class CauHoi {

    private String cauhoi;
    private String phuonganA;
    private String phuonganB;
    private String phuonganC;
    private String phuonganD;
    private String dapan;

    public CauHoi(String cauhoi, String phuonganA, String phuonganB, String phuonganC, String phuonganD, String dapan) {
        this.cauhoi = cauhoi;
        this.phuonganA = phuonganA;
        this.phuonganB = phuonganB;
        this.phuonganC = phuonganC;
        this.phuonganD = phuonganD;
        this.dapan = dapan;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getPhuonganA() {
        return phuonganA;
    }

    public void setPhuonganA(String phuonganA) {
        this.phuonganA = phuonganA;
    }

    public String getPhuonganB() {
        return phuonganB;
    }

    public void setPhuonganB(String phuonganB) {
        this.phuonganB = phuonganB;
    }

    public String getPhuonganC() {
        return phuonganC;
    }

    public void setPhuonganC(String phuonganC) {
        this.phuonganC = phuonganC;
    }

    public String getPhuonganD() {
        return phuonganD;
    }

    public void setPhuonganD(String phuonganD) {
        this.phuonganD = phuonganD;
    }

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }

    public static CauHoi ParseJSON(JSONObject job) throws JSONException {
        return  new CauHoi(
                job.getString("cauhoi"),
                job.getString("panA"),
                job.getString("panB"),
                job.getString("panC"),
                job.getString("panD"),
                job.getString("dapan")
        );
    }

}