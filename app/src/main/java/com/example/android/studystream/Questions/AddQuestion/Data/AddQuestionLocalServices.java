package com.example.android.studystream.Questions.AddQuestion.Data;

public interface AddQuestionLocalServices {
    void insertQuestion(int courseCode , int lessonNumber , int questionNumber ,String email ,String date,
                        String title,String content);
    int  getNewQuestionNumber(int courseCode , int lessonNumber);
}
