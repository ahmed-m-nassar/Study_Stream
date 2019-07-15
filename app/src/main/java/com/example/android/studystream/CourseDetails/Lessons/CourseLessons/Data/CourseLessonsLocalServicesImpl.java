package com.example.android.studystream.CourseDetails.Lessons.CourseLessons.Data;

import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.CourseDetails.Lessons.CourseLessons.Data.Models.Lesson;

import java.util.ArrayList;

public class CourseLessonsLocalServicesImpl extends DBHelper implements CourseLessonsLocalServices{

    public CourseLessonsLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public ArrayList<Lesson> SelectAllLessons(int CourseCode) {
        //select * from Lesson where Course_Code = course_code
        String query="Select * from "+ StudyStreamContract.Lesson.Table_Name + " where "
                + StudyStreamContract.Lesson.Column_Course_Code+" =? ";
        String[] Argument = {Integer.toString(CourseCode)};
        Cursor cursor=Select(query,Argument);

        ArrayList<Lesson> lessons = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int courseCode = cursor.getInt(cursor.getColumnIndex(StudyStreamContract.Lesson.Column_Course_Code));
            String lessonTitle = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Lesson.Column_Title));
            int lessonNum = cursor.getInt(cursor.getColumnIndex(StudyStreamContract.Lesson.Column_Lesson_Num));

            lessons.add(new Lesson(lessonTitle , courseCode , lessonNum));
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        cursor.close();

        return lessons;
    }
}
