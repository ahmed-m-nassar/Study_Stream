package com.example.android.studystream.CourseDetails.Announcements.NewAnnouncement.Data;

public interface NewAnnouncementLocalServices {
    void    InsertAnnouncement(int course_code, int announcement_number,
                               String title, String Content, java.util.Date date , String doc_id);

    boolean CheckAnnouncement(String title , int CourseCode);

    int     NewAnnouncementID(int CourseCode);
}
