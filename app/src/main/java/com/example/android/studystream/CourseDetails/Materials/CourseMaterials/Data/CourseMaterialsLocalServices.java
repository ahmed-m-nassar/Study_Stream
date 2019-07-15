package com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Data;

import com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Data.Models.Material;

import java.util.ArrayList;

public interface CourseMaterialsLocalServices {

    ArrayList<Material> SelectAllMaterials(int CourseCode);

}
