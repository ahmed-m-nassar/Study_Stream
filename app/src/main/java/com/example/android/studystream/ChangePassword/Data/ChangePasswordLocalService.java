package com.example.android.studystream.ChangePassword.Data;

public interface ChangePasswordLocalService {
    boolean checkExistingUser(String email , String passord);

    void updatePassword(String email , String password);
}
