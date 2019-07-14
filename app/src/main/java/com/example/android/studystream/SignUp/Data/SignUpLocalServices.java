package com.example.android.studystream.SignUp.Data;

public interface SignUpLocalServices {

    boolean checkExistingUser(String email);

    void  insertNewUser(String email , String firstName , String lastName , String password , boolean userType);
}
