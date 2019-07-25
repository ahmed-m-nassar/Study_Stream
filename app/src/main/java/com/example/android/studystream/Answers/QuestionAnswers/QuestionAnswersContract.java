package com.example.android.studystream.Answers.QuestionAnswers;

import com.example.android.studystream.Answers.Models.Answer;
import com.example.android.studystream.Questions.Models.Question;

import java.util.ArrayList;

public interface QuestionAnswersContract {
    interface View {

        void activateUpVote(int pos);
        void activateDownVote(int pos);
        void disableVotes(int pos);
        void setRate(int rate  , int pos);

        void fillAnswersList(ArrayList<Answer> answers);

    }

    interface Presenter {

        void getAnswers(int courseCode, int lessonNumber ,int questionNumber , String email);

        void upVoteButtonClicked(int position , int courseCode, int lessonNumber,
                                 int questionNumber,int answerNumber, String email ) ;

        void downVoteButtonClicked(int position , int courseCode, int lessonNumber,
                                   int questionNumber,int answerNumber, String email ) ;


    }
}
