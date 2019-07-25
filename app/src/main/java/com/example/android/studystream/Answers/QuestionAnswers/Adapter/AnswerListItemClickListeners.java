package com.example.android.studystream.Answers.QuestionAnswers.Adapter;

import android.view.View;

public interface AnswerListItemClickListeners {
    View.OnClickListener UpVoteClickListener(int answerNumber , int currentRate, int position);
    View.OnClickListener DownVoteClickListener(int answerNumber , int currentRate, int position);
}
