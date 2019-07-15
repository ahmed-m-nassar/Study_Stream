package com.example.android.studystream.CourseDetails.Materials.NewMaterial;

public interface NewMaterialContract {
    interface View {
        void showMessage(String message);
        void finishScreen();
    }
    interface Presenter {
        void addMaterialButtonClicked(String materialTitle, String materialContent
                , int courseCode , String professor);
    }
}
