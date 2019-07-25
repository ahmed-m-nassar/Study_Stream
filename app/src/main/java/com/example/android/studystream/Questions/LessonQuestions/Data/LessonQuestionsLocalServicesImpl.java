package com.example.android.studystream.Questions.LessonQuestions.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.CourseDetails.Announcements.Models.Announcement;
import com.example.android.studystream.Questions.Models.Question;

import java.util.ArrayList;

public class LessonQuestionsLocalServicesImpl extends DBHelper implements LessonQuestionsLocalServices {

    public LessonQuestionsLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public ArrayList<Question> getQuestions(int courseCode, int lessonNumber) {
        String query="Select * From "+ StudyStreamContract.Questions.Table_Name+" Where " +
                StudyStreamContract.Questions.Column_Lesson_Num+" =? and "
                +  StudyStreamContract.Questions.Column_Course_Code+" =? ";

        String[] arguments = { String.valueOf(lessonNumber) , String.valueOf(courseCode)};

        Cursor cursor=getReadableDatabase().rawQuery(query,arguments);

        ArrayList<Question> questions = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String questionTitle = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Questions.Column_Title));
            int questionNum = cursor.getInt(cursor.getColumnIndex(StudyStreamContract.Questions.Column_Question_Num));
            String questionContent = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Questions.Column_Content));
            String questionDate = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Questions.Column_Date));
            String email = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Questions.Column_Stud_ID));

            questions.add(new Question(courseCode , lessonNumber , questionNum ,
                    questionDate , questionContent , questionTitle ,email ));
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        cursor.close();

        return questions;

    }

    @Override
    public int getQuestionRating(int courseCode, int lessonNumber, int questionNumber) {
        String query="Select Sum( "+ StudyStreamContract.USER_VOTE_QUESTION.Column_Rating+" ) from "
                + StudyStreamContract.USER_VOTE_QUESTION.Table_Name+" where " +
                StudyStreamContract.USER_VOTE_QUESTION.Column_Course_Code+" =? and " +
                StudyStreamContract.USER_VOTE_QUESTION.Column_Lesson_Num+" =?  and " +
                StudyStreamContract.USER_VOTE_QUESTION.Column_Question_Num+" =? ";

        String[] argument= {String.valueOf(courseCode),String.valueOf(lessonNumber),String.valueOf(questionNumber)};
        Cursor cursor=  Select(query,argument);
        cursor.moveToFirst();
        return cursor.getInt(0);

    }

    @Override
    public int getUserRateOnQuestion(int courseCode, int lessonNumber, int questionNumber, String email) {
        String query="Select * from "+ StudyStreamContract.USER_VOTE_QUESTION.Table_Name+" where " +
                StudyStreamContract.USER_VOTE_QUESTION.Column_Course_Code+" =? and "+
                StudyStreamContract.USER_VOTE_QUESTION.Column_Lesson_Num+" =? and "+
                StudyStreamContract.USER_VOTE_QUESTION.Column_Question_Num+" =? and "
                + StudyStreamContract.USER_VOTE_QUESTION.Column_USER_ID+" =? ";
        String[] Argument= {String.valueOf(courseCode),String.valueOf(lessonNumber),String.valueOf(questionNumber)
                ,email};
        Cursor cursor =Select(query,Argument);
        cursor.moveToFirst();

        try {
            int rate = cursor.getInt(cursor.getColumnIndex(StudyStreamContract.USER_VOTE_QUESTION.Column_Rating));
            return rate;
        } catch (Exception e) {

            return 0;
        }

    }

    @Override
    public boolean checkExistingUserVote(int courseCode, int lessonNumber, int questionNumber, String email) {
        String query="Select * from "+ StudyStreamContract.USER_VOTE_QUESTION.Table_Name
                +" where "+ StudyStreamContract.USER_VOTE_QUESTION.Column_Course_Code
                +" =? and "+ StudyStreamContract.USER_VOTE_QUESTION.Column_Lesson_Num
                +" =? and "+ StudyStreamContract.USER_VOTE_QUESTION.Column_Question_Num
                +" =? and "+ StudyStreamContract.USER_VOTE_QUESTION.Column_USER_ID+" =? ";
        String[] Argument= {String.valueOf(courseCode),String.valueOf(lessonNumber),
                String.valueOf(questionNumber),email};
        Cursor cursor = Select(query,Argument);
        return cursor.getCount() > 0;
    }

    @Override
    public void insertRate(int courseCode, int lessonNumber, int questionNumber, String email, int rate) {
        ContentValues contentvalues= new ContentValues();
        contentvalues.put(StudyStreamContract.USER_VOTE_QUESTION.Column_Course_Code,courseCode);
        contentvalues.put(StudyStreamContract.USER_VOTE_QUESTION.Column_Lesson_Num,lessonNumber);
        contentvalues.put(StudyStreamContract.USER_VOTE_QUESTION.Column_Question_Num,questionNumber);
        contentvalues.put(StudyStreamContract.USER_VOTE_QUESTION.Column_USER_ID,email);
        contentvalues.put(StudyStreamContract.USER_VOTE_QUESTION.Column_Rating,rate);

        insert(StudyStreamContract.USER_VOTE_QUESTION.Table_Name,contentvalues);

    }

    @Override
    public void updateRate(int courseCode, int lessonNumber, int questionNumber, String email, int rate) {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(StudyStreamContract.USER_VOTE_QUESTION.Column_Rating , rate);
        String whereclause= StudyStreamContract.USER_VOTE_QUESTION.Column_Course_Code +
                " =? and "+ StudyStreamContract.USER_VOTE_QUESTION.Column_Lesson_Num+
                " =? and "+ StudyStreamContract.USER_VOTE_QUESTION.Column_Question_Num +
                " =? and "+ StudyStreamContract.USER_VOTE_QUESTION.Column_USER_ID+" =?";

        String[] Argument= {String.valueOf(courseCode),String.valueOf(lessonNumber),String.valueOf(questionNumber),email};
        update(StudyStreamContract.USER_VOTE_QUESTION.Table_Name,contentvalues,Argument,whereclause);

    }

    @Override
    public void deleteRate(int courseCode, int lessonNumber, int questionNumber, String email) {
        String whereclause = StudyStreamContract.USER_VOTE_QUESTION.Column_Course_Code + " =? AND "
                + StudyStreamContract.USER_VOTE_QUESTION.Column_Lesson_Num + " =? AND "
                + StudyStreamContract.USER_VOTE_QUESTION.Column_Question_Num + " =? AND " +
                StudyStreamContract.USER_VOTE_QUESTION.Column_USER_ID + " =?";
        String[] arguments = {String.valueOf(courseCode),String.valueOf(lessonNumber) , String.valueOf(questionNumber)
                                ,email};
        deleteWithoutCascade(StudyStreamContract.USER_VOTE_QUESTION.Table_Name,arguments,whereclause);
    }
}
