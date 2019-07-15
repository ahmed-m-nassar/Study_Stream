package com.example.android.studystream.CourseDetails.Materials.NewMaterial;

import com.example.android.studystream.CourseDetails.Materials.NewMaterial.Data.NewMaterialLocalServiceImpl;

import java.util.Calendar;
import java.util.Date;

public class NewMaterialPresenter implements NewMaterialContract.Presenter {
    private NewMaterialContract.View mView;
    private NewMaterialLocalServiceImpl mModel;

    public NewMaterialPresenter(NewMaterialContract.View mView) {
        this.mView = mView;
        mModel = new NewMaterialLocalServiceImpl();
    }

    @Override
    public void addMaterialButtonClicked(String materialTitle, String materialContent, int courseCode, String professor) {
        //validating fields
        ////////////////////////////////////////////////////////////

        //checking empty fields
        if(materialTitle.isEmpty() || materialContent.isEmpty()) {
            mView.showMessage("Please fill all fields");
            return;
        }

        //checking if the title is already used
        if(mModel.CheckMaterial(materialTitle , courseCode)) {
            mView.showMessage("Title already used , please pick another one");
            return;
        }
        ////////////////////////////////////////////////////////

        //adding material
        ////////////////////////////////////////////////////

        //getting new ID
        int id = mModel.NewMaterialID(courseCode);
        Date date = Calendar.getInstance().getTime();
        mModel.InsertMaterial(courseCode , id , materialTitle , materialContent ,
                date ,professor);
        mView.showMessage("Material added successfully");
        mView.finishScreen();
        ////////////////////////////////////////////////////////////
    }
}
