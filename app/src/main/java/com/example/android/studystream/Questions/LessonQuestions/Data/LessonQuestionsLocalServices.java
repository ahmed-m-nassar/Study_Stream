package com.example.android.studystream.Questions.LessonQuestions.Data;

import com.example.android.studystream.Questions.Models.Question;

import java.util.ArrayList;

public interface LessonQuestionsLocalServices {
    ArrayList<Question> getQuestions(int courseCode , int lessonNumber);

    int getQuestionRating(int courseCode , int lessonNumber , int questionNumber);

    int getUserRateOnQuestion(int courseCode, int lessonNumber, int questionNumber, String email);

    boolean checkExistingUserVote(int courseCode, int lessonNumber, int questionNumber, String email);

    void insertRate(int courseCode , int lessonNumber , int questionNumber , String email , int rate);

    void updateRate(int courseCode , int lessonNumber , int questionNumber , String email , int rate);

    void deleteRate(int courseCode , int lessonNumber , int questionNumber , String email);


}
