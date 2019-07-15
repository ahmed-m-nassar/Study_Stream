package com.example.android.studystream.CourseDetails.Materials.EditMaterial;

public interface EditMaterialContract {
    interface View {
        void showMessage(String message);

        void finishScreen();


    }

    interface Presenter {

        void  updateButtonClicked(int materialNum, int courseNum
                ,String originalTitle ,String materialTitle, String materialContent);

        void  deleteButtonClicked(int materialNum , int courseNum);

    }
}
