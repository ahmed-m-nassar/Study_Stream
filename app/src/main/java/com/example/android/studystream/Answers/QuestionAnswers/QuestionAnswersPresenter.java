package com.example.android.studystream.Answers.QuestionAnswers;

import com.example.android.studystream.Answers.Models.Answer;
import com.example.android.studystream.Answers.QuestionAnswers.Data.QuestionAnswersLocalServicesImpl;
import com.example.android.studystream.Questions.Models.Question;

import java.util.ArrayList;

public class QuestionAnswersPresenter implements QuestionAnswersContract.Presenter {

    private QuestionAnswersContract.View       mView;
    private QuestionAnswersLocalServicesImpl   mModel;

    public QuestionAnswersPresenter(QuestionAnswersContract.View mView) {
        this.mView = mView;
        mModel = new QuestionAnswersLocalServicesImpl();
    }

    @Override
    public void getAnswers(int courseCode, int lessonNumber, int questionNumber, String email) {
        ArrayList<Answer> answers = mModel.getAnswers(courseCode , lessonNumber , questionNumber);

        //checking user rating and settng the total rating of the answer
        for(int i = 0 ; i < answers.size() ; i++) {
            int answerNumber = answers.get(i).getmAnswerNumber();
            int userVote = mModel.getUserRateOnAnswer(courseCode ,lessonNumber ,questionNumber ,answerNumber ,email);
            int totalRate = mModel.getAnswerRating(courseCode ,lessonNumber ,questionNumber,answerNumber);
            answers.get(i).setmAnswerRate(totalRate);
            answers.get(i).setmStudentRate(userVote);
        }
        mView.fillAnswersList(answers);
    }

    @Override
    public void upVoteButtonClicked(int position, int courseCode, int lessonNumber, int questionNumber, int answerNumber, String email) {
        int userVote = mModel.getUserRateOnAnswer(courseCode ,lessonNumber ,questionNumber , answerNumber ,email);
        int currentRating = mModel.getAnswerRating(courseCode,lessonNumber,questionNumber , answerNumber);

        if(userVote > 0) {
            mView.disableVotes(position);
            mView.setRate(currentRating - 1, position);

            //updating user vote in database
            mModel.updateRate(courseCode,lessonNumber,questionNumber , answerNumber,email,0);
        } else if(userVote == 0) {
            mView.activateUpVote(position);
            mView.setRate(currentRating + 1, position);

            //updating uservote in database
            boolean voteExists = mModel.checkExistingUserVote(courseCode,lessonNumber,questionNumber
                    , answerNumber,email);
            if(voteExists) { //if user already voted update his vote
                mModel.updateRate(courseCode,lessonNumber,questionNumber,answerNumber,email,1);
            } else { //if user didnt vote insert a new vote
                mModel.insertRate(courseCode ,lessonNumber,questionNumber,answerNumber,email,1);
            }
        } else {
            mView.disableVotes(position);
            mView.activateUpVote(position);
            mView.setRate(currentRating + 2, position);

            //updating user vote in database
            mModel.updateRate(courseCode,lessonNumber,questionNumber , answerNumber,email,1);
        }
    }

    @Override
    public void downVoteButtonClicked(int position, int courseCode, int lessonNumber, int questionNumber, int answerNumber, String email) {
        int userVote = mModel.getUserRateOnAnswer(courseCode ,lessonNumber ,questionNumber,answerNumber ,email);
        int currentRating = mModel.getAnswerRating(courseCode,lessonNumber,questionNumber,answerNumber);

        if(userVote > 0) {
            mView.disableVotes(position);
            mView.activateDownVote(position);
            mView.setRate(currentRating - 2 , position);

            //updating user vote in database
            mModel.updateRate(courseCode,lessonNumber,questionNumber ,answerNumber,email,-1);
        } else if(userVote == 0) {
            mView.activateDownVote(position);
            mView.setRate(currentRating - 1, position);

            //updating uservote in database
            boolean voteExists = mModel.checkExistingUserVote(courseCode,lessonNumber,questionNumber,answerNumber,email);
            if(voteExists) { //if user already voted update his vote
                mModel.updateRate(courseCode,lessonNumber,questionNumber,answerNumber,email,-1);
            } else { //if user didnt vote insert a new vote
                mModel.insertRate(courseCode ,lessonNumber,questionNumber,answerNumber,email,-1);
            }
        } else {
            mView.disableVotes(position);
            mView.setRate(currentRating + 1, position);

            //updating user vote in database
            mModel.updateRate(courseCode,lessonNumber,questionNumber,answerNumber,email,0);
        }
    }
}
