package com.example.android.studystream.CourseDetails.Materials.NewMaterial.Data;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;

import java.util.Date;

public class NewMaterialLocalServiceImpl extends DBHelper implements NewMaterialLocalServices {
    public NewMaterialLocalServiceImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public void InsertMaterial(int courseCode, int materialNumber, String title, String Content, Date date, String doc_id) {
        ContentValues contentValues = new ContentValues();

        //mapping values to corresponding columns
        contentValues.put(StudyStreamContract.Materials.Column_Course_Code,courseCode);
        contentValues.put(StudyStreamContract.Materials.Column_Material_Num,materialNumber);
        contentValues.put(StudyStreamContract.Materials.Column_Title,title);
        contentValues.put(StudyStreamContract.Materials.Column_Content,Content);
        contentValues.put(StudyStreamContract.Materials.Column_Date,date.toString());
        contentValues.put(StudyStreamContract.Materials.Column_Doc_ID,doc_id);

        insert(StudyStreamContract.Materials.Table_Name,contentValues);
    }

    @Override
    public boolean CheckMaterial(String title, int CourseCode) {
        String query = "select * from " + StudyStreamContract.Materials.Table_Name
                + " where " + StudyStreamContract.Materials.Column_Title + " = '"
                + title + "'  And " + StudyStreamContract.Materials.Column_Course_Code
                + " = " + CourseCode;

        Cursor cursor= Select(query,null);
        int  count = cursor.getCount();
        cursor.close();
        if (count >= 1)
            return true;
        else
            return false;
    }

    @Override
    public int NewMaterialID(int CourseCode) {
        //select Max(Material_Number) from Materials
        Cursor mCount= getReadableDatabase().rawQuery("select MAX("+ StudyStreamContract.Materials.Column_Material_Num
                +") from " + StudyStreamContract.Materials.Table_Name + " Where " + StudyStreamContract.Materials.Column_Course_Code + " = " + CourseCode  , null);
        mCount.moveToFirst();
        int maxID= mCount.getInt(0);
        mCount.close();
        return maxID + 1;
    }
}
