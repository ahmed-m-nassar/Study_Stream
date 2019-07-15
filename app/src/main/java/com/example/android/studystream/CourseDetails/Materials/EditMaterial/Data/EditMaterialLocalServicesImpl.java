package com.example.android.studystream.CourseDetails.Materials.EditMaterial.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;

public class EditMaterialLocalServicesImpl extends DBHelper implements EditMaterialLocalServices {
    public EditMaterialLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public boolean CheckMaterial(String title, int CourseCode) {
        String query = "select * from " + StudyStreamContract.Materials.Table_Name
                + " where " + StudyStreamContract.Materials.Column_Title + " = '" + title +
                "'  And " + StudyStreamContract.Materials.Column_Course_Code
                + " = " + CourseCode;

        Cursor mCount= Select(query,null);
        int  count = mCount.getCount();
        mCount.close();

        if (count >= 1)
            return true;
        else
            return false;
    }

    @Override
    public void DeleteMaterials(int courseCode, int matNum) {
        String whereclause = StudyStreamContract.Materials.Column_Course_Code + " =? AND "
                + StudyStreamContract.Materials.Column_Material_Num + " =?";
        String[] arguments = {String.valueOf(courseCode),String.valueOf(matNum)};
        deleteWithoutCascade(StudyStreamContract.Materials.Table_Name,arguments,whereclause);
    }

    @Override
    public void UpdateMaterial(int courseCode, int matNum, String title, String content) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudyStreamContract.Materials.Column_Title,title);
        contentValues.put(StudyStreamContract.Materials.Column_Content,content);

        String whereclause = StudyStreamContract.Materials.Column_Course_Code + " =? AND "
                + StudyStreamContract.Materials.Column_Material_Num + " =?";
        String[] arguments = {String.valueOf(courseCode),String.valueOf(matNum)};
        update(StudyStreamContract.Materials.Table_Name,contentValues,arguments,whereclause);
    }
}
