package com.example.android.studystream.Questions.Models;

public class QuestionVote {
    private String mCourseCode;
    private String mLessonNumber;
    private String mQuestionNumber;
    private String mUserEmail;
    private String mRate;

    public QuestionVote(String mCourseCode, String mLessonNumber, String mQuestionNumber, String mUserEmail, String mRate) {
        this.mCourseCode = mCourseCode;
        this.mLessonNumber = mLessonNumber;
        this.mQuestionNumber = mQuestionNumber;
        this.mUserEmail = mUserEmail;
        this.mRate = mRate;
    }

    public String getmCourseCode() {
        return mCourseCode;
    }

    public String getmLessonNumber() {
        return mLessonNumber;
    }

    public String getmQuestionNumber() {
        return mQuestionNumber;
    }

    public String getmUserEmail() {
        return mUserEmail;
    }

    public String getmRate() {
        return mRate;
    }
}
