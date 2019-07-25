package com.example.android.studystream.CoursesStatistics.Model;

public class Statistics {
    private String mCourseTitle;
    private int mLessonsNum;
    private int mMaterialsNum;
    private int mAnnouncementsNum;
    private int mQuestionsNum;
    private int mAnswersNum;
    private int mStudentsNum;

    public Statistics(String mCourseTitle, int mLessonsNum, int mMaterialsNum, int mAnnouncementsNum, int mQuestionsNum, int mAnswersNum, int mStudentsNum) {
        this.mCourseTitle = mCourseTitle;
        this.mLessonsNum = mLessonsNum;
        this.mMaterialsNum = mMaterialsNum;
        this.mAnnouncementsNum = mAnnouncementsNum;
        this.mQuestionsNum = mQuestionsNum;
        this.mAnswersNum = mAnswersNum;
        this.mStudentsNum = mStudentsNum;
    }

    public String getmCourseTitle() {
        return mCourseTitle;
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
