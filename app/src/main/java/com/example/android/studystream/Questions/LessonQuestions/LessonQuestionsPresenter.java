package com.example.android.studystream.Questions.LessonQuestions;

import com.example.android.studystream.Questions.LessonQuestions.Data.LessonQuestionsLocalServicesImpl;
import com.example.android.studystream.Questions.Models.Question;

import java.util.ArrayList;

public class LessonQuestionsPresenter implements LessonQuestionsContract.Presenter{

    private LessonQuestionsContract.View     mView;
    private LessonQuestionsLocalServicesImpl mModel;

    public LessonQuestionsPresenter(LessonQuestionsContract.View mView) {
        this.mView = mView;
        mModel = new LessonQuestionsLocalServicesImpl();
    }

    @Override
    public void getQuestions(int courseCode, int lessonNumber , String email) {
        ArrayList<Question> questions = mModel.getQuestions(courseCode , lessonNumber);

        //checking user rating and settng the total rating of the question
        for(int i = 0 ; i<questions.size() ; i++) {
            int questionNumber = questions.get(i).getmQuestionNumber();
            int userVote = mModel.getUserRateOnQuestion(courseCode ,lessonNumber ,questionNumber ,email);
            int totalRate = mModel.getQuestionRating(courseCode ,lessonNumber ,questionNumber);
            questions.get(i).setmQuestionRate(totalRate);
            questions.get(i).setmStudentRate(userVote);
        }
        mView.fillQuestionList(questions);
    }

    @Override
    public void upVoteButtonClicked(int questionPosition , int courseCode, int lessonNumber, int questionNumber, String email ) {

        int userVote = mModel.getUserRateOnQuestion(courseCode ,lessonNumber ,questionNumber ,email);
        int currentRating = mModel.getQuestionRating(courseCode,lessonNumber,questionNumber);

        if(userVote > 0) {
            mView.disableVotes(questionPosition);
            mView.setRate(currentRating - 1, questionPosition);

            //updating user vote in database
            mModel.updateRate(courseCode,lessonNumber,questionNumber,email,0);
        } else if(userVote == 0) {
            mView.activateUpVote(questionPosition);
            mView.setRate(currentRating + 1, questionPosition);

            //updating uservote in database
            boolean voteExists = mModel.checkExistingUserVote(courseCode,lessonNumber,questionNumber,email);
            if(voteExists) { //if user already voted update his vote
                mModel.updateRate(courseCode,lessonNumber,questionNumber,email,1);
            } else { //if user didnt vote insert a new vote
                mModel.insertRate(courseCode ,lessonNumber,questionNumber,email,1);
            }
        } else {
            mView.disableVotes(questionPosition);
            mView.activateUpVote(questionPosition);
            mView.setRate(currentRating + 2, questionPosition);

            //updating user vote in database
            mModel.updateRate(courseCode,lessonNumber,questionNumber,email,1);
        }

    }

    @Override
    public void downVoteButtonClicked(int questionPosition , int courseCode, int lessonNumber, int questionNumber, String email ) {
        int userVote = mModel.getUserRateOnQuestion(courseCode ,lessonNumber ,questionNumber ,email);
        int currentRating = mModel.getQuestionRating(courseCode,lessonNumber,questionNumber);

        if(userVote > 0) {
            mView.disableVotes(questionPosition);
            mView.activateDownVote(questionPosition);
            mView.setRate(currentRating - 2 , questionPosition);

            //updating user vote in database
            mModel.updateRate(courseCode,lessonNumber,questionNumber,email,-1);
        } else if(userVote == 0) {
            mView.activateDownVote(questionPosition);
            mView.setRate(currentRating - 1, questionPosition);

            //updating uservote in database
            boolean voteExists = mModel.checkExistingUserVote(courseCode,lessonNumber,questionNumber,email);
            if(voteExists) { //if user already voted update his vote
                mModel.updateRate(courseCode,lessonNumber,questionNumber,email,-1);
            } else { //if user didnt vote insert a new vote
                mModel.insertRate(courseCode ,lessonNumber,questionNumber,email,-1);
            }
        } else {
            mView.disableVotes(questionPosition);
            mView.setRate(currentRating + 1, questionPosition);

            //updating user vote in database
            mModel.updateRate(courseCode,lessonNumber,questionNumber,email,0);
        }
    }


    @Override
    public void questionClicked(int questionNumber) {
            mView.navigateToAnswersActivity(questionNumber);
    }
}
