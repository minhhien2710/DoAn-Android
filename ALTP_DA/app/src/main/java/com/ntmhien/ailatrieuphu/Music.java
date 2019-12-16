package com.ntmhien.ailatrieuphu;

public class Music{
    private String Title;
    private int File;

    public Music(String title, int file) {
        Title = title;
        File = file;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getFile() {
        return File;
    }

    public void setFile(int file) {
        File = file;
    }
}