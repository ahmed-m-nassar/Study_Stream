package com.example.android.studystream.NewCourse;

public interface NewCourseContract {
    interface View {
        void showMessage(String message);

        void finishScreen();
    }
    interface Presenter {
        void addCourseButtonClicked(String courseCode, String courseTitle,
        String coursePassword, String courseDescription , String courseProfessor) ;
    }
}
