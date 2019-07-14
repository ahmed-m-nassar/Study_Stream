package com.example.android.studystream.JoinCourse;

public interface JoinCourseContract {
    interface View {
        void showMessage(String message);
        void finishScreen();
    }

    interface Presenter {
        void joinButtonClicked(int courseCode , String coursePass , String email);
    }
}
