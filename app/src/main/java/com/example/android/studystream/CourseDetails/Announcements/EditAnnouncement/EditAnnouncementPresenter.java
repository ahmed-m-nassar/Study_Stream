package com.example.android.studystream.CourseDetails.Announcements.EditAnnouncement;

import com.example.android.studystream.CourseDetails.Announcements.EditAnnouncement.Data.EditAnnouncementLocalServicesImpl;

public class EditAnnouncementPresenter implements EditAnnouncementContract.Presenter {

    private EditAnnouncementLocalServicesImpl mModel;
    private EditAnnouncementContract.View     mView;


    public EditAnnouncementPresenter(EditAnnouncementContract.View mView) {
        this.mView = mView;
        mModel = new EditAnnouncementLocalServicesImpl();
    }

    @Override
    public void updateButtonClicked(int announcementNum, int courseNum, String announcementTitle, String announcementContent , String announcementOriginalTitle) {
        //validating fields
        //////////////////////////////////////////////////////////

        //checking empty fields
        if(announcementTitle.isEmpty() || announcementContent.isEmpty()) {
            mView.showMessage("Please fill all fields");
            return;
        }

        //checking if the title is already used
        if(mModel.CheckAnnouncement(announcementTitle , courseNum) && !announcementTitle.equals( announcementOriginalTitle) ) {
            mView.showMessage("Title already used , please pick another one");
            return;
        }
        ////////////////////////////////////////////////////////

        //updating announcement
        mModel.UpdateAnnouncement(courseNum ,announcementNum , announcementTitle , announcementContent);
        mView.showMessage("Announcement updated successfully");
        mView.finishScreen();

    }

    @Override
    public void deleteButtonClicked(int announcementNum, int courseNum) {

        mModel.DeleteAnnouncement(courseNum , announcementNum);
        mView.showMessage("Announcement deleted successfully!");
        mView.finishScreen();
    }
}
