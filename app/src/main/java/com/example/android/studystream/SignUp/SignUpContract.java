package com.example.android.studystream.SignUp;

public interface SignUpContract {
    interface View {
        void showMessage (String message);
        void finishScreen();

        void navigateToCourseScreen(String email , boolean userType);
    }

    interface Presenter {
        void signUpButtonClicked(String email , String password , String confirmPassword
                ,String firstName , String lastName , boolean studentRadio , boolean doctorRadio);

    }
}
