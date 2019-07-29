package com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements;

import com.example.android.studystream.CourseDetails.Announcements.Models.Announcement;

import java.util.ArrayList;

public interface CourseAnnouncementsContract {
    interface View {
        void showMessage(String message);

        void fillAnnouncementsList(ArrayList<Announcement> announcements);

        void navigateToNewAnnouncementScreen();

    }
    interface Presenter {
        void getAnnouncementsList(int courseCode);

        void addAnnouncementButtonClicked();
    }
}
