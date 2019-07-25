package com.example.android.studystream.Questions.LessonQuestions.Adapter;

import android.view.View;

public interface QuestionListItemClickListeners {
    View.OnClickListener QuestionItemCLickListener(int questionNum , int currentRate);
    View.OnClickListener UpVoteClickListener(int questionNum , int currentRate, int questionPosition);
    View.OnClickListener DownVoteClickListener(int questionNum , int currentRate, int questionPosition);

}