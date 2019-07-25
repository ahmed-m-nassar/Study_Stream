package com.example.android.studystream.Answers.AddAnswer;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.android.studystream.Answers.AddAnswer.Data.AddAnswerApiService;
import com.example.android.studystream.Answers.AddAnswer.Data.AddAnswerLocalServices;
import com.example.android.studystream.Answers.AddAnswer.Data.AddAnswerLocalServicesImpl;
import com.example.android.studystream.Questions.AddQuestion.AddQuestionContract;
import com.example.android.studystream.Questions.AddQuestion.Data.AddQuestionLocalServicesImpl;

import java.time.LocalDate;

public class AddAnswerPresenter implements AddAnswerContract.Presenter {
    private AddAnswerLocalServices mModel;
    private AddAnswerContract.View mView;

    public AddAnswerPresenter(AddAnswerContract.View mView) {
        this.mView = mView;
        mModel = new AddAnswerLocalServicesImpl();
    }

    @Override
    public void addAnswerButtonClicked(String content, int courseCode, int lessonNum, int questionNumber, String email) {
        //validating fields
        if(content.isEmpty()) {
            mView.showMessage("Please add your answer");
            return;
        }

        //getting a new question number
        int answerNumber = mModel.getNewAnswerNumber(courseCode , lessonNum , questionNumber);

        //getting today`s date
        String date = "1/1/1999";

        mModel.insertAnswer(courseCode , lessonNum , questionNumber , answerNumber , email ,date  ,content);
        mView.showMessage("Answer added successfully!");
        mView.finishScreen();
    }
}
