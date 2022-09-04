package com.example.myapplication3;

import android.graphics.drawable.Drawable;

public class planitem {

    private String pday;
    private String pcnt;
    private String pspot;
    private String paddress;
    private String pcontent;
    //private String pimage;

    public planitem(String pday, String pcnt, String pspot, String paddress, String pcontent) {
        this.pday = pday;
        this.pcnt = pcnt;
        this.pspot = pspot;
        this.paddress = paddress;
        this.pcontent = pcontent;
        //this.pimage = pimgae;

    }

    public String getPday() {
        return pday;
    }

    public void setPday(String pday) {
        this.pday = pday;
    }

    public String getPcnt() {
        return pcnt;
    }

    public void setPcnt(String pcnt) {
        this.pcnt = pcnt;
    }

    public String getPspot() {
        return pspot;
    }

    public void setPspot(String pspot) {
        this.pspot = pspot;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }

    //public void setPimage(String pimage){ this.pimage = pimage;}
    //public String getPimage(){return pimage;}
}
