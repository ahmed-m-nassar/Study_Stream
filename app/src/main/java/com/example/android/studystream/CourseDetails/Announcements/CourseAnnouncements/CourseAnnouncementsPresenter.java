package com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements;

import com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Data.CourseAnnouncementsLocalServicesImpl;
import com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Data.Models.Announcement;

import java.util.ArrayList;

public class CourseAnnouncementsPresenter implements CourseAnnouncementsContract.Presenter {
    private CourseAnnouncementsLocalServicesImpl mModel;
    private CourseAnnouncementsContract.View     mView;

    public CourseAnnouncementsPresenter(CourseAnnouncementsContract.View mView) {
        this.mView = mView;
        mModel = new CourseAnnouncementsLocalServicesImpl();
    }

    @Override
    public void getAnnouncementsList(int courseCode) {
       ArrayList<Announcement> announcements = mModel.SelectAllAnnouncements(courseCode);
       mView.fillAnnouncementsList(announcements);
    }

    @Override
    public void addAnnouncementButtonClicked() {
        mView.navigateToNewAnnouncementScreen();
    }
}
