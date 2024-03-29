package com.example.android.studystream.CourseDetails.Announcements.NewAnnouncement;

public interface NewAnnouncementContract {
    interface View {
        void showMessage(String message);
        void finishScreen();
    }
    interface Presenter {
        void addAnnouncementButtonClicked(String announcementTitle, String announcementContent
                , int courseCode , String professor);
    }
}
