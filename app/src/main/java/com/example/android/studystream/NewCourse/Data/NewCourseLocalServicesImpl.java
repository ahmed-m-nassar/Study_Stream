package com.example.android.studystream.NewCourse.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.Courses.Data.Models.Course;

public class NewCourseLocalServicesImpl extends DBHelper implements NewCourseLocalServices {
    public NewCourseLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public boolean checkExistingCourse(String courseCode) {

        String query= "Select * from " + StudyStreamContract.Course.Table_Name +
                " where "+ StudyStreamContract.Course.Column_Code+" =? " ;

        String[] Argument = {courseCode};
        Cursor cursor = Select(query,Argument);

        if(cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

    @Override
    public void addNewCourse(String courseCode, String courseTitle,
                             String coursePassword, String courseDescription , String courseProfessor) {
        ContentValues content = new ContentValues();
        content.put(StudyStreamContract.Course.Column_Code , courseCode);
        content.put(StudyStreamContract.Course.Column_Title , courseTitle);
        content.put(StudyStreamContract.Course.Column_Password , coursePassword);
        content.put(StudyStreamContract.Course.Column_Description , courseDescription);
        content.put(StudyStreamContract.Course.Column_Doc_ID , courseProfessor);

        insert(StudyStreamContract.Course.Table_Name,content);

    }
}
