package com.example.myapplication3;

public class moneyitem {
    private String day;
    private String cnt;
    private String title;
    private String price;

    public moneyitem(String day, String cnt, String title, String price) {
        this.day = day;
        this.cnt = cnt;
        this.title = title;
        this.price = price;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
