package com.example.android.studystream.CourseDetails.CourseAnnouncements.Data.Models;

public class Announcement {
    private String mTitle;
    private int mCourseCode;
    private int mAnnouncementNum;
    private String mContent;
    private String mProfessor;
    private String mAnnouncementDate;
    public Announcement(String title ,String content,int courseCode,
                        int announcementNum , String professor , String announcementDate)
    {
        mTitle = title;
        mCourseCode = courseCode;
        mAnnouncementNum = announcementNum;
        mContent = content;
        mProfessor = professor;
        mAnnouncementDate = announcementDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public int getmCourseCode() {
        return mCourseCode;
    }

    public int getmAnnouncementNum() {
        return mAnnouncementNum;
    }

    public String getmContent() {
        return mContent;
    }

    public String getmProfessor() {
        return mProfessor;
    }

    public String getmAnnouncementDate() {
        return mAnnouncementDate;
    }
}
