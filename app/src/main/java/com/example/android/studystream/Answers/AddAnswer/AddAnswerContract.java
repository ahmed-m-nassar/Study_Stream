package com.example.android.studystream.Answers.AddAnswer;

public interface AddAnswerContract {
    interface View {
        void showMessage(String message);
        void finishScreen();
    }

    interface Presenter {
        void addAnswerButtonClicked( String content , int courseCode , int lessonNum
                ,int questionNumber ,String email);
    }
}
