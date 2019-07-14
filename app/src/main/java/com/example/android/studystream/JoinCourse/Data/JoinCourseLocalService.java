package com.example.android.studystream.JoinCourse.Data;

public interface JoinCourseLocalService {

    boolean checkExistingCourse (int courseCode , String coursePass);

    boolean checkJoinedByStudent(String email , int courseCode);

    void joinCourse(String email , int courseCode);
}
