package com.example.android.studystream.CourseDetails.Materials.EditMaterial;

import com.example.android.studystream.CourseDetails.Materials.EditMaterial.Data.EditMaterialLocalServicesImpl;

public class EditMaterialPresenter implements EditMaterialContract.Presenter {

    private EditMaterialLocalServicesImpl mModel;
    private EditMaterialContract.View     mView;

    public EditMaterialPresenter(EditMaterialContract.View mView) {
        this.mView = mView;

        mModel = new EditMaterialLocalServicesImpl();
    }

    @Override
    public void updateButtonClicked(int materialNum, int courseNum
            ,String originalTitle ,String materialTitle, String materialContent) {
        //validating fields
        ////////////////////////////////////////////////////////////

        //checking empty fields
        if(materialTitle.isEmpty() || materialContent.isEmpty()) {
            mView.showMessage("Please fill all fields");
            return;
        }

        //checking if the title is already used
        if(mModel.CheckMaterial(materialTitle , courseNum) && !materialTitle.equals(originalTitle)) {
            mView.showMessage("Title already used , please pick another one");
            return;
        }
        ////////////////////////////////////////////////////////


        //updating material
        mModel.UpdateMaterial(courseNum , materialNum , materialTitle , materialContent);
        mView.showMessage("Material updated successfully!");
        mView.finishScreen();
    }

    @Override
    public void deleteButtonClicked(int materialNum, int courseNum) {
        mModel.DeleteMaterials(courseNum , materialNum);
        mView.showMessage("Material deleted successfully!");
        mView.finishScreen();
    }
}
