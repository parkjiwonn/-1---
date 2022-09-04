package com.example.myapplication3;

public class DiaryInfo {
    private String title;
    private String place;
    private String start;
    private String finish;

    public DiaryInfo(String title, String place, String start, String finish) {
        this.title = title;
        this.place = place;
        this.start = start;
        this.finish = finish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }
}
