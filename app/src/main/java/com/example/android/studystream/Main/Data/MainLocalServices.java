package com.example.android.studystream.Main.Data;

import com.example.android.studystream.Main.Model.User;

public interface MainLocalServices {
    String checkLoggedInUser();
    boolean checkUserType(String email);
}
