package com.example.android.studystream.Questions.Models;

public class Question {
    private int mCourseCode;
    private int mLessonNumber;
    private int mQuestionNumber;
    private int mQuestionRate;
    private int mStudentRate;
    private String mQuestionDate;
    private String mQuestionContent;
    private String mQuestionTitle;
    private String mStudentEmail;

    public Question(int mCourseCode, int mLessonNumber, int mQuestionNumber, String mQuestionDate, String mQuestionContent, String mQuestionTitle, String mStudentEmail) {
        this.mCourseCode = mCourseCode;
        this.mLessonNumber = mLessonNumber;
        this.mQuestionNumber = mQuestionNumber;
        this.mQuestionDate = mQuestionDate;
        this.mQuestionContent = mQuestionContent;
        this.mQuestionTitle = mQuestionTitle;
        this.mStudentEmail = mStudentEmail;
    }

    public void setmQuestionRate(int mQuestionRate) {
        this.mQuestionRate = mQuestionRate;
    }

    public int getmCourseCode() {
        return mCourseCode;
    }

    public int getmLessonNumber() {
        return mLessonNumber;
    }

    public int getmQuestionNumber() {
        return mQuestionNumber;
    }

    public int getmQuestionRate() {
        return mQuestionRate;
    }

    public int getmStudentRate() {
        return mStudentRate;
    }

    public String getmQuestionDate() {
        return mQuestionDate;
    }

    public String getmQuestionContent() {
        return mQuestionContent;
    }

    public String getmQuestionTitle() {
        return mQuestionTitle;
    }

    public String getmStudentEmail() {
        return mStudentEmail;
    }

    public void setmStudentRate(int mStudentRate) {
        this.mStudentRate = mStudentRate;
    }
}
