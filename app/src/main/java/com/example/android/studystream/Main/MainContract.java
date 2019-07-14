package com.example.android.studystream.Main;

public interface MainContract {
    interface View {

        /**
         * this method navigates to sign up screen
         */
        void navigateToSignUpScreen();

        /**
         * this method navigates to sign in screen
         */
        void navigateToSignInScreen();
    }

    interface Presenter {

        /**
         * this method navigates to sign up screen when sign up button is clicked
         */
        void SignUpButtonClicked();

        /**
         * this method navigates to sign in screen when sign in button is clicked
         */
        void SignInButtonClicked();
    }
}
