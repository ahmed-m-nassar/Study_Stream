package com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Data.Models;

public class Material {
    private String mTitle;
    private String mContent;
    private int mCourseCode;
    private int mMaterialNum;
    private String mMaterialDate;
    private String mProfessor;

    public Material(String mTitle, String mContent, int mCourseCode, int mMaterialNum, String mMaterialDate, String mProfessor) {
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mCourseCode = mCourseCode;
        this.mMaterialNum = mMaterialNum;
        this.mMaterialDate = mMaterialDate;
        this.mProfessor = mProfessor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public int getmCourseCode() {
        return mCourseCode;
    }

    public int getmMaterialNum() {
        return mMaterialNum;
    }

    public String getmMaterialDate() {
        return mMaterialDate;
    }

    public String getmProfessor() {
        return mProfessor;
    }
}
