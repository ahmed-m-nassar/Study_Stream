package com.example.android.studystream.Answers.QuestionAnswers.Data;

import com.example.android.studystream.Answers.Models.Answer;
import com.example.android.studystream.Questions.Models.Question;

import java.util.ArrayList;

public interface QuestionAnswersLocalServices {
    ArrayList<Answer> getAnswers(int courseCode , int lessonNumber , int questionNumber);

    int getAnswerRating(int courseCode , int lessonNumber , int questionNumber , int answerNumber);

    int getUserRateOnAnswer(int courseCode, int lessonNumber, int questionNumber , int answerNumber, String email);

    boolean checkExistingUserVote(int courseCode, int lessonNumber, int questionNumber, int answerNumber, String email);

    void insertRate(int courseCode , int lessonNumber , int questionNumber, int answerNumber , String email , int rate);

    void updateRate(int courseCode , int lessonNumber , int questionNumber, int answerNumber , String email , int rate);

}
