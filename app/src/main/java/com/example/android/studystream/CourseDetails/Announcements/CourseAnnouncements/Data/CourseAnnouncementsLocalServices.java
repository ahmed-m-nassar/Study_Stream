package com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Data;


import com.example.android.studystream.CourseDetails.Announcements.Models.Announcement;

import java.util.ArrayList;

public interface CourseAnnouncementsLocalServices {
    ArrayList<Announcement> SelectAllAnnouncements(int CourseCode);


}
