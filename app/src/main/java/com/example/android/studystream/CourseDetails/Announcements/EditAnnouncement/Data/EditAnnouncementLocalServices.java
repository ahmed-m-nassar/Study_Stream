package com.example.android.studystream.CourseDetails.Announcements.EditAnnouncement.Data;

public interface EditAnnouncementLocalServices {
    boolean CheckAnnouncement(String title , int CourseCode);

    void DeleteAnnouncement(int courseCode , int matNum);

    void UpdateAnnouncement(int courseCode,int annNum,String title , String content);
}
