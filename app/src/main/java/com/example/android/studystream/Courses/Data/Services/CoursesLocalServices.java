package com.example.android.studystream.Courses.Data.Services;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;

import com.example.android.studystream.Courses.Data.Models.Course;

import java.util.ArrayList;

public interface CoursesLocalServices {
    String getDoctorBio(String email);

    ArrayList<Course> getDoctorCourses(String email);
    ArrayList<Course> getStudentCourses(String email);


}
