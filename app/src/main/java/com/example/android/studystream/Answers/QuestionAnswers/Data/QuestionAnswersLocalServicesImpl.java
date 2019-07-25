package com.example.android.studystream.Answers.QuestionAnswers.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Answers.Models.Answer;
import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.Questions.Models.Question;

import java.util.ArrayList;

public class QuestionAnswersLocalServicesImpl extends DBHelper implements QuestionAnswersLocalServices {
    public QuestionAnswersLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public ArrayList<Answer> getAnswers(int courseCode, int lessonNumber, int questionNumber) {


        String query="Select * From "+ StudyStreamContract.Answers.Table_Name+
                " Where "+ StudyStreamContract.Answers.Column_Lesson_Num
                +" =? and "+ StudyStreamContract.Answers.Column_Course_Code+" =? and "+
                StudyStreamContract.Answers.Column_Question_Num+" =? ";

        String[] arguments = { String.valueOf(lessonNumber) , String.valueOf(courseCode) , String.valueOf(questionNumber)};

        Cursor cursor = getReadableDatabase().rawQuery(query,arguments);

        ArrayList<Answer> answers = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int answerNumber = cursor.getInt(cursor.getColumnIndex(StudyStreamContract.Answers.Column_Answer_Num));
            String answerContent = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Answers.Column_Content));
            String answerDate = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Answers.Column_Date));
            String email = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Answers.Column_User_ID));

            answers.add(new Answer(courseCode , lessonNumber , answerNumber ,
                    answerDate , answerContent  ,email ));
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        cursor.close();

        return answers;

    }

    @Override
    public int getAnswerRating(int courseCode, int lessonNumber, int questionNumber, int answerNumber) {
        String query="Select Sum( "+ StudyStreamContract.USER_VOTE_ANSWERS.Column_Rating+" ) from " +
                StudyStreamContract.USER_VOTE_ANSWERS.Table_Name+" where " +
                StudyStreamContract.USER_VOTE_ANSWERS.Column_Course_Code+" =? and " +
                StudyStreamContract.USER_VOTE_ANSWERS.Column_Lesson_Num+" =?  and " +
                StudyStreamContract.USER_VOTE_ANSWERS.Column_Question_Num+" =? and " +
                StudyStreamContract.USER_VOTE_ANSWERS.Column_Answer_Num+" =?";


        String[] argument= {String.valueOf(courseCode),String.valueOf(lessonNumber),String.valueOf(questionNumber) , String.valueOf(answerNumber)};
        Cursor cursor=  Select(query,argument);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    @Override
    public int getUserRateOnAnswer(int courseCode, int lessonNumber, int questionNumber, int answerNumber, String email) {
        String query="Select * from "+ StudyStreamContract.USER_VOTE_ANSWERS.Table_Name+" where " +
                StudyStreamContract.USER_VOTE_ANSWERS.Column_Course_Code+" =? and "+
                StudyStreamContract.USER_VOTE_ANSWERS.Column_Lesson_Num+" =? and "+
                StudyStreamContract.USER_VOTE_ANSWERS.Column_Question_Num+" =? and "+
                StudyStreamContract.USER_VOTE_ANSWERS.Column_Answer_Num+" =? and "+
                StudyStreamContract.USER_VOTE_ANSWERS.Column_USER_ID+" =? ";
        String[] Argument= {String.valueOf(courseCode),String.valueOf(lessonNumber)
                ,String.valueOf(questionNumber),String.valueOf(answerNumber),email};
        Cursor cursor =Select(query,Argument);
        cursor.moveToFirst();

        try {
            int rate = cursor.getInt(cursor.getColumnIndex(StudyStreamContract.USER_VOTE_ANSWERS.Column_Rating));
            return rate;
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public boolean checkExistingUserVote(int courseCode, int lessonNumber, int questionNumber, int answerNumber, String email) {
        String query="Select * from "+ StudyStreamContract.USER_VOTE_ANSWERS.Table_Name
                +" where "+  StudyStreamContract.USER_VOTE_ANSWERS.Column_Course_Code
                +" =? and "+ StudyStreamContract.USER_VOTE_ANSWERS.Column_Lesson_Num
                +" =? and "+ StudyStreamContract.USER_VOTE_ANSWERS.Column_Question_Num
                +" =? and "+ StudyStreamContract.USER_VOTE_ANSWERS.Column_Answer_Num
                +" =? and "+ StudyStreamContract.USER_VOTE_ANSWERS.Column_USER_ID+" =? ";
        String[] Argument= {String.valueOf(courseCode),String.valueOf(lessonNumber),
                String.valueOf(questionNumber) ,String.valueOf(answerNumber) ,email};
        Cursor cursor = Select(query,Argument);
        return cursor.getCount() > 0;
    }

    @Override
    public void insertRate(int courseCode, int lessonNumber, int questionNumber, int answerNumber, String email, int rate) {
        ContentValues contentvalues= new ContentValues();
        contentvalues.put(StudyStreamContract.USER_VOTE_ANSWERS.Column_Course_Code,courseCode);
        contentvalues.put(StudyStreamContract.USER_VOTE_ANSWERS.Column_Lesson_Num,lessonNumber);
        contentvalues.put(StudyStreamContract.USER_VOTE_ANSWERS.Column_Question_Num,questionNumber);
        contentvalues.put(StudyStreamContract.USER_VOTE_ANSWERS.Column_Answer_Num,answerNumber);
        contentvalues.put(StudyStreamContract.USER_VOTE_ANSWERS.Column_USER_ID,email);
        contentvalues.put(StudyStreamContract.USER_VOTE_ANSWERS.Column_Rating,rate);

        insert(StudyStreamContract.USER_VOTE_ANSWERS.Table_Name,contentvalues);
    }

    @Override
    public void updateRate(int courseCode, int lessonNumber, int questionNumber, int answerNumber, String email, int rate) {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(  StudyStreamContract.USER_VOTE_ANSWERS.Column_Rating , rate);
        String whereclause= StudyStreamContract.USER_VOTE_ANSWERS.Column_Course_Code +
                " =? and "+ StudyStreamContract.USER_VOTE_ANSWERS.Column_Lesson_Num+
                " =? and "+ StudyStreamContract.USER_VOTE_ANSWERS.Column_Question_Num +
                " =? and "+ StudyStreamContract.USER_VOTE_ANSWERS.Column_Answer_Num +
                " =? and "+ StudyStreamContract.USER_VOTE_ANSWERS.Column_USER_ID+" =?";

        String[] Argument= {String.valueOf(courseCode),String.valueOf(lessonNumber),
                String.valueOf(questionNumber),String.valueOf(answerNumber),email};
        update(StudyStreamContract.USER_VOTE_ANSWERS.Table_Name,contentvalues,Argument,whereclause);

    }
}
