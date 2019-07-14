package com.example.android.studystream.Courses;

import com.example.android.studystream.Courses.Data.Models.Course;

import java.util.ArrayList;

public interface CoursesContract {
    interface View {
        void fillCoursesList(ArrayList<Course> courses);
        void fillDoctorBio(String bio);

        void navigateToCourseLessonsScreen();
        void navigateToChangePasswordScreen();
        void navigateToCoursesStatisticsScreen();
        void navigateToNewCourseScreen();
        void navigateToChangeBioScreen();
        void navigateToJoinCourseScreen();
    }

    interface Presenter {
        void getDoctorData(String email);
        void getStudentData(String email);

    }
}
