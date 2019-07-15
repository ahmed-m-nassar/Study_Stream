package com.example.android.studystream.CourseDetails.Materials.EditMaterial.Data;

public interface EditMaterialLocalServices {

    boolean CheckMaterial(String title , int CourseCode);

    void DeleteMaterials(int courseCode , int matNum);

    void UpdateMaterial(int courseCode,int matNum,String title , String content);

}
