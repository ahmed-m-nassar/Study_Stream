package com.example.android.studystream.CourseDetails.Materials.CourseMaterials;

import com.example.android.studystream.CourseDetails.Materials.Models.Material;

import java.util.ArrayList;

public interface CourseMaterialsContract {
    interface View {
        void showMessage(String message);

        void fillAnnouncementsList(ArrayList<Material> materials);

        void navigateToNewMaterialScreen();

        void navigateToMaterialDetailsScreen();
    }
    interface Presenter {
        void getMaterialsList(int courseCode);

        void addMaterialButtonClicked();
    }
}
