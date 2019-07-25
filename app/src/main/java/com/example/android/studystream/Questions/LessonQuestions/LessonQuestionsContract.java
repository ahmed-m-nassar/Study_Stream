package com.example.android.studystream.Questions.LessonQuestions;

import com.example.android.studystream.Questions.Models.Question;

import java.util.ArrayList;

public interface LessonQuestionsContract {
    interface View {

        void activateUpVote(int pos);
        void activateDownVote(int pos);
        void disableVotes(int pos);
        void setRate(int rate  , int pos);

        void fillQuestionList(ArrayList<Question> questions);

        void navigateToAnswersActivity(int questionNumber);

    }

    interface Presenter {

        void getQuestions(int courseCode, int lessonNumber , String email);

        void upVoteButtonClicked(int questionPosition , int courseCode, int lessonNumber, int questionNumber, String email ) ;
        void downVoteButtonClicked(int questionPosition , int courseCode, int lessonNumber, int questionNumber, String email ) ;
        void questionClicked(int questionNumber);

    }
}
