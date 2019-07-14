package com.example.android.studystream.SignIn;

public interface SignInContract {
    interface View {
        void showMessage (String message);

        void navigateToCoursesScreen(String Email , boolean userType);
    }

    interface Presenter {

        void signInButtonClicked(String email , String password);
    }
}
