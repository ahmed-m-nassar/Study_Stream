package com.example.android.studystream.Invite.Model;

public class Contract {
    String name;
    String phone;

    public Contract(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
