package com.example.android.studystream.CourseDetails.CourseAnnouncements.Data;


import com.example.android.studystream.CourseDetails.CourseAnnouncements.Data.Models.Announcement;

import java.util.ArrayList;

public interface CourseAnnouncementsLocalServices {
    ArrayList<Announcement> SelectAllAnnouncements(int CourseCode);


}
