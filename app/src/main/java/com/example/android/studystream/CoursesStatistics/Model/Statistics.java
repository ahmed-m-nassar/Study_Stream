package com.example.android.studystream.CoursesStatistics.Model;

public class Statistics {
    private int mCourseCode;
    private int mLessonsNum;
    private int mMaterialsNum;
    private int mAnnouncementsNum;
    private int mQuestionsNum;
    private int mAnswersNum;
    private int mStudentsNum;

    public Statistics(int mCourseCode, int mLessonsNum, int mMaterialsNum, int mAnnouncementsNum, int mQuestionsNum, int mAnswersNum, int mStudentsNum) {
        this.mCourseCode = mCourseCode;
        this.mLessonsNum = mLessonsNum;
        this.mMaterialsNum = mMaterialsNum;
        this.mAnnouncementsNum = mAnnouncementsNum;
        this.mQuestionsNum = mQuestionsNum;
        this.mAnswersNum = mAnswersNum;
        this.mStudentsNum = mStudentsNum;
    }

    public int getmCourseTitle() {
        return mCourseCode;
    }

    public int getmLessonsNum() {
        return mLessonsNum;
    }

    public int getmMaterialsNum() {
        return mMaterialsNum;
    }

    public int getmAnnouncementsNum() {
        return mAnnouncementsNum;
    }

    public int getmQuestionsNum() {
        return mQuestionsNum;
    }

    public int getmAnswersNum() {
        return mAnswersNum;
    }

    public int getmStudentsNum() {
        return mStudentsNum;
    }
}
