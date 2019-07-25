package com.example.android.studystream.Main.Model;

public class User {
    private String  mEmail;
    private String  mPassword;
    private boolean mUserType;

    public User(String mEmail, String mPassword, boolean mUserType) {
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mUserType = mUserType;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public boolean ismUserType() {
        return mUserType;
    }
}
