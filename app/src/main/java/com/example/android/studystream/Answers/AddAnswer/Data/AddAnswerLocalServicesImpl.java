package com.example.android.studystream.Answers.AddAnswer.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;

public class AddAnswerLocalServicesImpl extends DBHelper implements AddAnswerLocalServices {
    public AddAnswerLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public void insertAnswer(int courseCode, int lessonNumber, int questionNumber, int answerNumber, String email, String date, String content) {
        ContentValues contentValues = new ContentValues();

        //mapping values to corresponding columns
        contentValues.put(StudyStreamContract.Answers.Column_Course_Code,courseCode);
        contentValues.put(StudyStreamContract.Answers.Column_Lesson_Num,lessonNumber);
        contentValues.put(StudyStreamContract.Answers.Column_Question_Num,questionNumber);
        contentValues.put(StudyStreamContract.Answers.Column_Answer_Num,answerNumber);
        contentValues.put(StudyStreamContract.Answers.Column_User_ID,email);
        contentValues.put(StudyStreamContract.Answers.Column_Date,date);
        contentValues.put(StudyStreamContract.Answers.Column_Content,content);

        insert(StudyStreamContract.Answers.Table_Name,contentValues);
    }

    @Override
    public int getNewAnswerNumber(int courseCode, int lessonNumber, int questionNumber) {

        String query = ("select max( " + StudyStreamContract.Answers.Column_Answer_Num
                + " ) from " + StudyStreamContract.Answers.Table_Name + " Where " +
                StudyStreamContract.Answers.Column_Course_Code + " = ? and "+
                StudyStreamContract.Answers.Column_Lesson_Num+" =? and " +
                StudyStreamContract.Answers.Column_Question_Num + " =?");
        String[] arguments = {String.valueOf(courseCode),String.valueOf(lessonNumber) ,
                              String.valueOf(questionNumber)};

        Cursor mCount = Select(query, arguments);
        mCount.moveToFirst();

        int maxID = mCount.getInt(0);

        mCount.close();

        return maxID + 1;
    }
}
