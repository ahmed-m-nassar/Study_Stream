package com.example.android.studystream.CourseDetails.CourseLessons.Data.Models;

public class Lesson {
    private String mTitle;
    private int mCourseCode;
    private int mLessonNum;
    public Lesson(String title ,int courseCode,int lessonNum)
    {
        mTitle = title;
        mCourseCode = courseCode;
        mLessonNum = lessonNum;
    }

    public int GetCourseCode()
    {
        return mCourseCode;
    }
    public int GetLessonNum()

    {
        return  mLessonNum;
    }
    public String GetTitle()
    {
        return mTitle;
    }

}
