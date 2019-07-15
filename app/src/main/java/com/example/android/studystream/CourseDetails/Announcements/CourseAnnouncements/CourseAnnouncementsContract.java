package com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements;

import com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Data.Models.Announcement;

import java.util.ArrayList;

public interface CourseAnnouncementsContract {
    interface View {
        void showMessage(String message);

        void fillAnnouncementsList(ArrayList<Announcement> announcements);

        void navigateToNewAnnouncementScreen();

        void navigateToAnnouncementDetailsScreen();
    }
    interface Presenter {
        void getAnnouncementsList(int courseCode);

        void addAnnouncementButtonClicked();
    }
}
