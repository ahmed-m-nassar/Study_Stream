package com.example.android.studystream.SignIn.Data;

import android.database.Cursor;

public interface SignInLocalServices {

    boolean checkExistingUser(String email , String password);

    boolean checkDoctorUser (String email);

    Cursor  getUserData (String email);

}
