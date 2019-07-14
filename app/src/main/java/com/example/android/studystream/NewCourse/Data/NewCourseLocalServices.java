package com.example.android.studystream.NewCourse.Data;

public interface NewCourseLocalServices {
    boolean checkExistingCourse(String courseCode);

    void addNewCourse(String courseCode , String courseTitle ,
                      String coursePassword , String courseDescription , String courseProfessor);
}
