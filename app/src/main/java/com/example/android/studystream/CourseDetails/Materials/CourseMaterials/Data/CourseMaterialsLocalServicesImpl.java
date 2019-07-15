package com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Data;

import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Data.Models.Material;

import java.util.ArrayList;

public class CourseMaterialsLocalServicesImpl extends DBHelper implements CourseMaterialsLocalServices {
    public CourseMaterialsLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public ArrayList<Material> SelectAllMaterials(int CourseCode) {
        //select * from Mwterials where Course_Code = course_code Order by date desc
        String query="Select * from "+ StudyStreamContract.Materials.Table_Name +
                " where "+ StudyStreamContract.Materials.Column_Course_Code+" = "
                + CourseCode ;

        Cursor cursor=getReadableDatabase().rawQuery(query,null);

        ArrayList<Material> materials = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int courseCode = cursor.getInt(cursor.getColumnIndex(StudyStreamContract.Materials.Column_Course_Code));
            String materialTitle = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Materials.Column_Title));
            int materialNum = cursor.getInt(cursor.getColumnIndex(StudyStreamContract.Materials.Column_Material_Num));
            String materialContent = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Materials.Column_Content));
            String materialProfessor = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Materials.Column_Doc_ID));
            String materialDate = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Materials.Column_Date));


            materials.add(new Material(materialTitle , materialContent , courseCode ,
                    materialNum , materialDate , materialProfessor));
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        cursor.close();

        return materials;

    }


}
