package com.example.android.studystream.CourseDetails.Lessons.NewLesson.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;

public class NewLessonLocalServicesImpl extends DBHelper implements NewLessonLocalServices {

    public NewLessonLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public void InsertLesson(int course_code, int lesson_number, String title) {
        ContentValues contentValues = new ContentValues();

        //mapping values to corresponding columns
        contentValues.put(StudyStreamContract.Lesson.Column_Course_Code,course_code);
        contentValues.put(StudyStreamContract.Lesson.Column_Lesson_Num,lesson_number);
        contentValues.put(StudyStreamContract.Lesson.Column_Title,title);

        insert(StudyStreamContract.Lesson.Table_Name,contentValues);
    }

    @Override
    public boolean CheckLesson(String title, int CourseCode) {
        String query = "select * from " + StudyStreamContract.Lesson.Table_Name
                + " where " + StudyStreamContract.Lesson.Column_Title + " = '" +
                title + "'  And " + StudyStreamContract.Lesson.Column_Course_Code
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
    public int NewLessonID(int CourseCode) {
        //select Max(Lesson_Number) from Lesson
        Cursor mCount= getReadableDatabase().rawQuery("select MAX("+ StudyStreamContract.Lesson.Column_Lesson_Num
                +") from " + StudyStreamContract.Lesson.Table_Name + " Where " +
                StudyStreamContract.Lesson.Column_Course_Code + " = " + CourseCode  , null);
        mCount.moveToFirst();
        int maxID= mCount.getInt(0);
        mCount.close();
        return maxID + 1;
    }
}
