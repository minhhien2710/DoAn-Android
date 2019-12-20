package com.ntmhien.ailatrieuphu.music;

public class Music{
    private String Title;
    private int File1;
    private int File2;

    public Music(String title, int file1, int file2) {
        Title = title;
        File1 = file1;
        File2 = file2;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getFile1() {
        return File1;
    }

    public void setFile1(int file1) {
        File1 = file1;
    }

    public int getFile2() {
        return File2;
    }

    public void setFile2(int file2) {
        File2 = file2;
    }
}