package com.example.android.studystream.EditBio;

import com.example.android.studystream.CourseDetails.Announcements.EditAnnouncement.Data.EditAnnouncementLocalServicesImpl;
import com.example.android.studystream.EditBio.Data.EditBioLocalServices;
import com.example.android.studystream.EditBio.Data.EditBioLocalServicesImpl;

public class EditBioPresenter implements EditBioContract.Presenter {
    private EditBioContract.View              mView;
    private EditBioLocalServicesImpl mModel;

    public EditBioPresenter(EditBioContract.View mView) {
        this.mView = mView;
        mModel = new EditBioLocalServicesImpl();
    }

    @Override
    public void EditBioButtonClicked(String bio, String email) {
        mModel.updateBio(bio , email);
        mView.showMessage("Bio updated Successfully");
        mView.finishScreen();
    }
}
