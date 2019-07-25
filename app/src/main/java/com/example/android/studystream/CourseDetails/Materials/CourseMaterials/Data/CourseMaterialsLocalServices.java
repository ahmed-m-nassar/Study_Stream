package com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Data;

import com.example.android.studystream.CourseDetails.Materials.Models.*;

import java.util.ArrayList;

public interface CourseMaterialsLocalServices {

    ArrayList<Material> SelectAllMaterials(int CourseCode);

}
