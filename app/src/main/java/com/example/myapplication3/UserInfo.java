package com.example.myapplication3;

public class UserInfo {
    private String Uemail;
    private String Upass;
    private String Uname;
    private int Uage;

    public UserInfo(String uemail,String upass, String uname, int uage) {
        this.Uemail = uemail;
        this.Upass = upass;
        this.Uname = uname;
        this.Uage = uage;
    }

    public String getUemail() {
        return Uemail;
    }

    public void setUemail(String uemail) {
        Uemail = uemail;
    }

    public String getUpass() {
        return Upass;
    }

    public void setUpass(String upass) {
        Upass = upass;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public int getUage() {
        return Uage;
    }

    public void setUage(int uage) {
        Uage = uage;
    }
}
