package com.example.android.studystream.CourseDetails.CourseMaterials.Data;

import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.CourseDetails.CourseAnnouncements.Data.Models.Announcement;
import com.example.android.studystream.CourseDetails.CourseLessons.Data.CourseLessonsLocalServices;
import com.example.android.studystream.CourseDetails.CourseLessons.Data.Models.Lesson;
import com.example.android.studystream.CourseDetails.CourseMaterials.Data.Models.Material;

import java.util.ArrayList;
import java.util.Date;

public class CourseMaterialsLocalServicesImpl extends DBHelper implements CourseMaterialsLocalServices {
    public CourseMaterialsLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public ArrayList<Material> SelectAllMaterials(int CourseCode) {
        //select * from Announcements where Course_Code = course_code Order by date desc
        String query="Select * from "+ StudyStreamContract.Materials.Table_Name +
                " where "+ StudyStreamContract.Materials.Column_Course_Code +
                " =?  Order by " + StudyStreamContract.Materials.Column_Date + " desc" ;

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

    @Override
    public void InsertMaterial(int course_code, int material_number, String title, String Content, Date date, String doc_id) {

    }

    @Override
    public void UpdateMaterial(int courseCode, int matNum, String title, String content) {

    }

    @Override
    public void DeleteMaterials(int courseCode, int matNum) {

    }

    @Override
    public boolean CheckMaterial(String title, int CourseCode) {
        return false;
    }

    @Override
    public int NewMaterialID(int CourseCode) {
        return 0;
    }
}
