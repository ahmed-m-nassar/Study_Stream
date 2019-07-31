package com.example.android.studystream.Main;

public interface MainContract {
    interface View {

        void navigateToCoursesScreen(String email , boolean userType);

        void finishScreen();
    }

    interface Presenter {



        void checkLoggedInUser();


    }
}
