package com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Adapter;

import android.view.View;

public interface AnnouncementListClickListeners {
    View.OnClickListener  announcementClicked(int announcementNumber , String announcementTitle , String announcementContent);
}
