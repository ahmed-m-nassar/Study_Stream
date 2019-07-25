package com.example.android.studystream.CoursesStatistics.Data;

import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;

public class CourseStatisticsLocalServicesImpl extends DBHelper implements CourseStatisticsLocalServices {
    public CourseStatisticsLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public int GetNumberOfLessons(int CourseNum) {
        String query="Select count(*) from "+ StudyStreamContract.Lesson.Table_Name +
                " where "+ StudyStreamContract.Lesson.Column_Course_Code+" =? ";
        String[] Argument = {String.valueOf(CourseNum)};
        Cursor cursor = Select(query,Argument);
        cursor.moveToFirst();
        int count = cursor.getInt(0); //getting count of Lessons
        return  count;
    }

    @Override
    public int GetNumberOfAnnouncements(int CourseNum) {

        String query="Select count(*) from "+ StudyStreamContract.Announcements.Table_Name
                + " where "+ StudyStreamContract.Announcements.Column_Course_Code + " =? ";
        String[] Argument = {String.valueOf(CourseNum)};
        Cursor cursor = Select(query,Argument);
        cursor.moveToFirst();
        int count = cursor.getInt(0); //getting count of annoucements
        return  count;
    }

    @Override
    public int GetNumberOfMaterials(int CourseNum) {
        String query="Select count(*) from "+ StudyStreamContract.Materials.Table_Name +
                " where "+ StudyStreamContract.Materials.Column_Course_Code+" =? ";
        String[] Argument = {String.valueOf(CourseNum)};
        Cursor cursor = Select(query,Argument);
        cursor.moveToFirst();
        int count = cursor.getInt(0); //getting count of Materials
        return  count;
    }

    @Override
    public int GetNumberOfQuestions(int CourseNum) {
        String query="Select count(*) from "+ StudyStreamContract.Questions.Table_Name +
                " where "+ StudyStreamContract.Questions.Column_Course_Code+" =? ";
        String[] Argument = {String.valueOf(CourseNum)};
        Cursor cursor = Select(query,Argument);
        cursor.moveToFirst();
        int count = cursor.getInt(0); //getting count of Questions
        return  count;
    }

    @Override
    public int GetNumberOfAnswers(int CourseNum) {
        String query="Select count(*) from "+ StudyStreamContract.Answers.Table_Name +
                " where "+ StudyStreamContract.Answers.Column_Course_Code+" =? ";
        String[] Argument = {String.valueOf(CourseNum)};
        Cursor cursor = Select(query,Argument);
        cursor.moveToFirst();
        int count = cursor.getInt(0); //getting count of Answers
        return  count;
    }

    @Override
    public int GetNumberOfStudents(int CourseNum) {
        String query="Select count(*) from "+ StudyStreamContract.STUD_COURSE.Table_Name + " where "
                + StudyStreamContract.STUD_COURSE.Column_Course_Code+" =? ";
        String[] Argument = {String.valueOf(CourseNum)};
        Cursor cursor=Select(query,Argument);
        cursor.moveToFirst();
        int count = cursor.getInt(0); //getting count of Students
        return  count;
    }
}
