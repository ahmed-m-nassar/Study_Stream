package com.example.android.studystream.Questions.AddQuestion;

public interface AddQuestionContract {
    interface View {
        void showMessage(String message);
        void finishScreen();
    }

    interface Presenter {
        void addQuestionButtonClicked(String title , String content , int courseCode , int lessonNum
                                        ,String email);
    }
}
