package com.example.android.studystream.CoursesHomePage;

import com.example.android.studystream.CoursesHomePage.Data.Models.Course;

import java.util.ArrayList;

public interface CoursesHomePageContract {
    interface View {
        void fillCoursesList(ArrayList<Course> courses);
        void fillDoctorBio(String bio);

        void navigateToCourseDetailsScreen();
        void navigateToChangePasswordScreen();
        void navigateToCoursesStatisticsScreen();
        void navigateToNewCourseScreen();
        void navigateToChangeBioScreen();
        void navigateToJoinCourseScreen();
        void navigateToMainScreen();

        void showMessage(String message);
        void finishScreen();
        void exitApplication();
    }

    interface Presenter {
        void getDoctorData(String email);
        void getStudentData(String email);

        void logoutItemClicked(String email);
        void courseStatisticsItemClicked();
        void changePasswordItemClicked();

        void backButtonPressed(long lastBackButtonPressingTime);

    }
}
