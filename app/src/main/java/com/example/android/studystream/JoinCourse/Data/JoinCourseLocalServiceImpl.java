package com.example.android.studystream.JoinCourse.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;

public class JoinCourseLocalServiceImpl extends DBHelper implements JoinCourseLocalService {

    public JoinCourseLocalServiceImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public boolean checkExistingCourse(int courseCode, String coursePass) {

        String query= "Select * from "+ StudyStreamContract.Course.Table_Name+" where " +
                StudyStreamContract.Course.Column_Code+" =? and "+ StudyStreamContract.Course.Column_Password+" =? " ;

        String[] Argument = {String.valueOf(courseCode) , coursePass};
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
    public boolean checkJoinedByStudent(String email, int courseCode) {
        String query= "Select * from "+ StudyStreamContract.STUD_COURSE.Table_Name+" where " +
                StudyStreamContract.STUD_COURSE.Column_Course_Code + " =? and "
                + StudyStreamContract.STUD_COURSE.Column_Stud_ID+" =? " ;

        String[] Argument = {String.valueOf(courseCode) , email};
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
    public void joinCourse(String email, int courseCode) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudyStreamContract.STUD_COURSE.Column_Course_Code,courseCode);
        contentValues.put(StudyStreamContract.STUD_COURSE.Column_Stud_ID, email);

        insert(StudyStreamContract.STUD_COURSE.Table_Name , contentValues);
    }
}
