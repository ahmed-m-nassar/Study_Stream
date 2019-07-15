package com.example.android.studystream.CourseDetails.Materials.NewMaterial.Data;

public interface NewMaterialLocalServices {
    void    InsertMaterial(int courseCode, int materialNumber,
                               String title, String Content, java.util.Date date , String doc_id);

    boolean CheckMaterial(String title , int CourseCode);

    int     NewMaterialID(int CourseCode);
}
