package com.example.android.studystream.Answers.AddAnswer.Data;

public class AddAnswerApiService implements AddAnswerLocalServices {
    @Override
    public void insertAnswer(int courseCode, int lessonNumber, int questionNumber, int answerNumber, String email, String date, String content) {

    }

    @Override
    public int getNewAnswerNumber(int courseCode, int lessonNumber, int questionNumber) {
        return 0;
    }
}
