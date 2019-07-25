package com.example.android.studystream.Answers.QuestionAnswers.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.studystream.Answers.Models.Answer;
import com.example.android.studystream.Questions.LessonQuestions.Adapter.QuestionListItemClickListeners;
import com.example.android.studystream.Questions.Models.Question;
import com.example.android.studystream.R;

import java.util.List;

public class AnswerListItemAdapter extends ArrayAdapter<Answer> {
private boolean mUserType;
private String  mUserEmail;
private Context mContext;
private AnswerListItemClickListeners mClickListeners;
public AnswerListItemAdapter(@NonNull Context context, List<Answer> answers , String email
        , boolean userType , AnswerListItemClickListeners clickListeners) {
        super(context, 0,answers);
        mUserType = userType;
        mUserEmail = email;
        mContext = context;
        mClickListeners = clickListeners;
        }

   @NonNull
   @Override
   public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    final Answer Item = getItem(position);
    View ListItemView = convertView;
        if(ListItemView == null)
        {
        ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.answer_list_item,parent,false);
        }

        TextView email =  (TextView) ListItemView.findViewById(R.id.AnswerListItem_UserId_TextView);
        TextView answerContent = (TextView) ListItemView.findViewById(R.id.AnswerListItem_AnswerContent_TextView);
        TextView answerRate = (TextView) ListItemView.findViewById(R.id.AnswerListItem_AnswerRate_TextView);
        TextView answerDate = (TextView) ListItemView.findViewById(R.id.AnswerListItem_AnswerDate_TextView);
        ImageView upVote = (ImageView)ListItemView.findViewById(R.id.AnswerListItem_UpVote_ImageView);
        ImageView downVote = (ImageView)ListItemView.findViewById(R.id.AnswerListItem_DownVote_ImageView);

        //setting data
        email.setText(Item.getmUserEmail().toString());
        answerContent.setText(Item.getmAnswerContent().toString());
        answerRate.setText(String.valueOf(Item.getmAnswerRate()));
        answerDate.setText(Item.getmAnswerDate());

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
        upVote.setOnClickListener( mClickListeners.UpVoteClickListener(Item.getmAnswerNumber()
        , Item.getmAnswerRate() , position));

        downVote.setOnClickListener(mClickListeners.DownVoteClickListener(Item.getmAnswerNumber()
        , Item.getmAnswerRate(), position));


        return ListItemView;
    }
}



