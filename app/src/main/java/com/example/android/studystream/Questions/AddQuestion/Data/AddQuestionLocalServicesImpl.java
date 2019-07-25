package com.example.android.studystream.Questions.AddQuestion.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.Questions.AddQuestion.AddQuestionContract;
import com.example.android.studystream.Questions.Models.Question;

public class AddQuestionLocalServicesImpl extends DBHelper implements AddQuestionLocalServices{
    public AddQuestionLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public void insertQuestion(int courseCode, int lessonNumber, int questionNumber, String email, String date, String title, String content) {
        ContentValues contentValues = new ContentValues();

        //mapping values to corresponding columns
        contentValues.put(StudyStreamContract.Questions.Column_Course_Code,courseCode);
        contentValues.put(StudyStreamContract.Questions.Column_Lesson_Num,lessonNumber);
        contentValues.put(StudyStreamContract.Questions.Column_Question_Num,questionNumber);
        contentValues.put(StudyStreamContract.Questions.Column_Stud_ID,email);
        contentValues.put(StudyStreamContract.Questions.Column_Date,date);
        contentValues.put(StudyStreamContract.Questions.Column_Title,title);
        contentValues.put(StudyStreamContract.Questions.Column_Content,content);

        insert(StudyStreamContract.Questions.Table_Name,contentValues);
    }

    @Override
    public int getNewQuestionNumber(int courseCode, int lessonNumber) {
        // Select Max(Announcement_Number) from Announcements
        String query = ("select max( " + StudyStreamContract.Questions.Column_Question_Num
                + " ) from " + StudyStreamContract.Questions.Table_Name + " Where " +
                StudyStreamContract.Questions.Column_Course_Code + " = ? and "+
                StudyStreamContract.Questions.Column_Lesson_Num+" =?");
        String[] arguments = {String.valueOf(courseCode),String.valueOf(lessonNumber)};

        Cursor mCount = Select(query, arguments);
        mCount.moveToFirst();

        int maxID = mCount.getInt(0);

        mCount.close();

        return maxID + 1;
    }
}
