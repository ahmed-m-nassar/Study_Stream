package com.example.android.studystream.Questions.AddQuestion;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.android.studystream.Questions.AddQuestion.Data.AddQuestionLocalServicesImpl;

import java.time.LocalDate;

public class AddQuestionPresenter implements AddQuestionContract.Presenter {
    private AddQuestionLocalServicesImpl mModel;
    private AddQuestionContract.View     mView;

    public AddQuestionPresenter(AddQuestionContract.View mView) {
        this.mView = mView;
        mModel = new AddQuestionLocalServicesImpl();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void addQuestionButtonClicked(String title, String content, int courseCode, int lessonNum, String email) {
        //validating fields
        if(title.isEmpty() || content.isEmpty()) {
            mView.showMessage("Please fill all fields");
            return;
        }

        //getting a new question number
        int questionNumber = mModel.getNewQuestionNumber(courseCode , lessonNum);

        //getting today`s date
        String date = "1/1/1999";

        mModel.insertQuestion(courseCode , lessonNum , questionNumber , email ,date ,title ,content);
        mView.showMessage("Question added successfully!");
        mView.finishScreen();
    }
}
