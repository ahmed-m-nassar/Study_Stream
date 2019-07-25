package com.example.android.studystream.Answers.Models;

public class Answer {
    private int mCourseCode;
    private int mLessonNumber;
    private int mAnswerNumber;
    private int mAnswerRate;
    private int mStudentRate;
    private String mAnswerDate;
    private String mAnswerContent;
    private String mUserEmail;

    public Answer(int mCourseCode, int mLessonNumber, int mAnswerNumber, String mAnswerDate, String mAnswerContent, String mUserEmail) {
        this.mCourseCode = mCourseCode;
        this.mLessonNumber = mLessonNumber;
        this.mAnswerNumber = mAnswerNumber;
        this.mAnswerDate = mAnswerDate;
        this.mAnswerContent = mAnswerContent;
        this.mUserEmail = mUserEmail;
    }

    public void setmAnswerRate(int mAnswerRate) {
        this.mAnswerRate = mAnswerRate;
    }

    public void setmStudentRate(int mStudentRate) {
        this.mStudentRate = mStudentRate;
    }

    public int getmStudentRate() {
        return mStudentRate;
    }

    public int getmCourseCode() {
        return mCourseCode;
    }

    public int getmLessonNumber() {
        return mLessonNumber;
    }

    public int getmAnswerNumber() {
        return mAnswerNumber;
    }

    public int getmAnswerRate() {
        return mAnswerRate;
    }

    public String getmAnswerContent() {
        return mAnswerContent;
    }

    public String getmUserEmail() {
        return mUserEmail;
    }

    public String getmAnswerDate() {
        return mAnswerDate;
    }
}
