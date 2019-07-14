package com.example.android.studystream.CourseDetails.CourseMaterials.Data;

import com.example.android.studystream.CourseDetails.CourseMaterials.Data.Models.Material;

import java.util.ArrayList;

public interface CourseMaterialsLocalServices {

    ArrayList<Material> SelectAllMaterials(int CourseCode);

    void    InsertMaterial(int course_code, int material_number, String title,
                        String Content, java.util.Date date , String doc_id);

    void    UpdateMaterial(int courseCode,int matNum,String title , String content);

    void    DeleteMaterials(int courseCode , int matNum);

    boolean CheckMaterial(String title , int CourseCode);

    int     NewMaterialID(int CourseCode);



}
