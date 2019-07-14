package com.example.android.studystream.CoursesHomePage.Data.Models;

public class Course {

    private String mCourseTitle;
    private int mCourseCode;
    private String mCoursePassword;
    private String mCourseDescription;
    private String mCourseProfessor;

    public Course(String mCourseTitle, int mCourseCode, String mCoursePassword, String mCourseDescription, String mCourseProfessor) {
        this.mCourseTitle = mCourseTitle;
        this.mCourseCode = mCourseCode;
        this.mCoursePassword = mCoursePassword;
        this.mCourseDescription = mCourseDescription;
        this.mCourseProfessor = mCourseProfessor;
    }

    public String getmCourseTitle() {
        return mCourseTitle;
    }

    public int getmCourseCode() {
        return mCourseCode;
    }

    public String getmCoursePassword() {
        return mCoursePassword;
    }

    public String getmCourseDescription() {
        return mCourseDescription;
    }

    public String getmCourseProfessor() {
        return mCourseProfessor;
    }
}
