package com.example.android.studystream.CourseDetails.Announcements.EditAnnouncement;

public interface EditAnnouncementContract {
    interface View {
        void showMessage(String message);

        void finishScreen();


    }

    interface Presenter {

            void updateButtonClicked(int announcementNum, int courseNum, String announcementTitle, String announcementContent , String announcementOriginalTitle);

            void  deleteButtonClicked(int announcementNum , int courseNum);

    }
}
