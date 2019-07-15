package com.example.android.studystream.CourseDetails.Materials.CourseMaterials;

import com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Data.CourseMaterialsLocalServicesImpl;
import com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Data.Models.Material;

import java.util.ArrayList;

public class CourseMaterialsPresenter implements CourseMaterialsContract.Presenter {
    private CourseMaterialsContract.View     mView;
    private CourseMaterialsLocalServicesImpl mModel;

    public CourseMaterialsPresenter(CourseMaterialsContract.View mView) {
        this.mView = mView;
        mModel = new CourseMaterialsLocalServicesImpl();
    }

    @Override
    public void getMaterialsList(int courseCode) {
        ArrayList<Material> materials = mModel.SelectAllMaterials(courseCode);
        mView.fillAnnouncementsList(materials);
    }

    @Override
    public void addMaterialButtonClicked() {
        mView.navigateToNewMaterialScreen();
    }

}
