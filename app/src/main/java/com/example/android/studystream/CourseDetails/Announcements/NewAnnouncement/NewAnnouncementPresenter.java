package com.example.android.studystream.CourseDetails.Announcements.NewAnnouncement;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.android.studystream.CourseDetails.Announcements.NewAnnouncement.Data.NewAnnouncementLocalServicesImpl;

import java.util.Calendar;
import java.util.Date;

public class NewAnnouncementPresenter implements NewAnnouncementContract.Presenter {

    private NewAnnouncementContract.View mView;
    private NewAnnouncementLocalServicesImpl mModel;

    public NewAnnouncementPresenter(NewAnnouncementContract.View mView) {
        this.mView = mView;
        mModel = new NewAnnouncementLocalServicesImpl();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void addAnnouncementButtonClicked(String announcementTitle, String announcementContent
            , int courseCode , String professor) {
        //validating fields
        //////////////////////////////////////////////////////////

        //checking empty fields
        if(announcementTitle.isEmpty() || announcementContent.isEmpty()) {
            mView.showMessage("Please fill all fields");
            return;
        }

        //checking if the title is already used
        if(mModel.CheckAnnouncement(announcementTitle , courseCode)) {
            mView.showMessage("Title already used , please pick another one");
            return;
        }
        ////////////////////////////////////////////////////////

        //adding announcement
        ////////////////////////////////////////////////////

        //getting new ID
        int id = mModel.NewAnnouncementID(courseCode);
        Date date = Calendar.getInstance().getTime();
        mModel.InsertAnnouncement(courseCode , id , announcementTitle , announcementContent ,
                   date ,professor);
        mView.showMessage("Announcement added successfully");
        mView.finishScreen();
        /////////////////////////////////////////////////////
    }
}
