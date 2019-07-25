package com.example.android.studystream.Answers.AddAnswer.Data;

public interface AddAnswerLocalServices {
    void insertAnswer(int courseCode , int lessonNumber , int questionNumber ,int answerNumber
            ,String email ,String date,String content);
    int  getNewAnswerNumber(int courseCode , int lessonNumber , int questionNumber);
}
