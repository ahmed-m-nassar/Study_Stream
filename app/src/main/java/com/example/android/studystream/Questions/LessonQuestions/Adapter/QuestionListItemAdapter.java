package com.example.android.studystream.Questions.LessonQuestions.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.studystream.CourseDetails.Announcements.Models.Announcement;
import com.example.android.studystream.Questions.Models.Question;
import com.example.android.studystream.R;

import java.util.List;

public class QuestionListItemAdapter extends ArrayAdapter<Question> {
    private boolean mUserType;
    private String  mUserEmail;
    private Context mContext;
    private QuestionListItemClickListeners mClickListeners;
    public QuestionListItemAdapter(@NonNull Context context, List<Question> question , String email
            , boolean userType ,QuestionListItemClickListeners clickListeners) {
        super(context, 0,question);
        mUserType = userType;
        mUserEmail = email;
        mContext = context;
        mClickListeners = clickListeners;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Question Item = getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.question_list_item,parent,false);
        }

        LinearLayout questionParent = (LinearLayout)ListItemView.findViewById(R.id.QuestionListItem_Parent_Layout);
        TextView questionTitle = (TextView) ListItemView.findViewById(R.id.QuestionListItem_Title_TextView);
        TextView questionContent = (TextView) ListItemView.findViewById(R.id.QuestionListItem_Content_TextView);
        TextView questionRate = (TextView) ListItemView.findViewById(R.id.QuestionListItem_Rate_TextView);
        TextView questionDate = (TextView) ListItemView.findViewById(R.id.QuestionListItem_Date_TextView);
        ImageView upVote = (ImageView)ListItemView.findViewById(R.id.QuestionListItem_UpVote_ImageView);
        ImageView downVote = (ImageView)ListItemView.findViewById(R.id.QuestionListItem_DownVote_ImageView);

        //setting data
        questionTitle.setText(Item.getmQuestionTitle().toString());
        questionContent.setText(Item.getmQuestionContent().toString());
        questionDate.setText(Item.getmQuestionDate());
        questionRate.setText(String.valueOf(Item.getmQuestionRate()));

        //initializing vote
        if(Item.getmStudentRate() > 0 ) {
            upVote.setImageResource(R.drawable.upvoteactivated);
            downVote.setImageResource(R.drawable.downvote);
        } else if (Item.getmStudentRate() < 0) {
            upVote.setImageResource(R.drawable.upvote);
            downVote.setImageResource(R.drawable.downvoteactivated);
        } else {
            upVote.setImageResource(R.drawable.upvote);
            downVote.setImageResource(R.drawable.downvote);
        }

        //click listeners
        // TODO : is it better to use interface on click listeners like this?
        questionParent.setOnClickListener( mClickListeners.QuestionItemCLickListener(Item.getmQuestionNumber() ,
                   Item.getmQuestionRate()));
        upVote.setOnClickListener( mClickListeners.UpVoteClickListener(Item.getmQuestionNumber()
                , Item.getmQuestionRate() , position));
        downVote.setOnClickListener(mClickListeners.DownVoteClickListener(Item.getmQuestionNumber()
                , Item.getmQuestionRate(), position));


        return ListItemView;
    }


}
