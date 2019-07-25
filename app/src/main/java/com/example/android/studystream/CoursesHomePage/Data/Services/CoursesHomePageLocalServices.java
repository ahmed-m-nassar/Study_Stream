package com.example.android.studystream.CoursesHomePage.Data.Services;

import com.example.android.studystream.CoursesHomePage.Data.Models.Course;

import java.util.ArrayList;

public interface CoursesHomePageLocalServices {
    String getDoctorBio(String email);

    ArrayList<Course> getDoctorCourses(String email);
    ArrayList<Course> getStudentCourses(String email);

    void logOutUser(String email);

}
