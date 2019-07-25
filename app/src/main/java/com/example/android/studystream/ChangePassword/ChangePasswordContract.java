package com.example.android.studystream.ChangePassword;

public interface ChangePasswordContract {

    interface View {

        void finishScreen();
        void showMessage(String message);
    }
    interface Presenter {
        void saveButtonClicked(String email , String oldPassword , String newPassword , String confirmPassowd);
    }
}
