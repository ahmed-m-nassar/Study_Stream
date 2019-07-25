package com.example.android.studystream.Answers.QuestionAnswers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.studystream.Answers.AddAnswer.AddAnswerActivity;
import com.example.android.studystream.Answers.Models.Answer;
import com.example.android.studystream.Answers.QuestionAnswers.Adapter.AnswerListItemAdapter;
import com.example.android.studystream.Answers.QuestionAnswers.Adapter.AnswerListItemClickListeners;
import com.example.android.studystream.Questions.AddQuestion.AddQuestionActivity;
import com.example.android.studystream.Questions.LessonQuestions.Adapter.QuestionListItemAdapter;
import com.example.android.studystream.Questions.LessonQuestions.LessonQuestionsPresenter;
import com.example.android.studystream.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswersActivity extends AppCompatActivity implements QuestionAnswersContract.View, AnswerListItemClickListeners
{

    private QuestionAnswersPresenter    mPresenter;
    private ListView                    mList;
    private Button                      mAddAnswer;
    private String                      mEmail;
    private boolean                     mUserType;
    private int                         mCourseCode;
    private int                         mLessonNumber;
    private int                         mQuestionNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answers);
        //setting private variables
        /////////////////////////////////////////////////////////////////////

        //presenter
        mPresenter = new QuestionAnswersPresenter(this);

        //views
        mList = (ListView)findViewById(R.id.QuestionAnswers_Answers_ListView);
        mAddAnswer = (Button)findViewById(R.id.QuestionAnswers_AddAnswer_Button);

        //extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");
        mCourseCode = intent.getIntExtra("CourseCode" , -1);
        mLessonNumber = intent.getIntExtra("LessonNumber" , -1);
        mQuestionNumber = intent.getIntExtra("QuestionNumber" , -1);
        mUserType = intent.getBooleanExtra("UserType" , false);

        //////////////////////////////////////////////////////////////////////
        mPresenter.getAnswers(mCourseCode , mLessonNumber ,mQuestionNumber  , mEmail);

        //add question click listener
        mAddAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , AddAnswerActivity.class);
                intent.putExtra("CourseCode" , mCourseCode);
                intent.putExtra("LessonNumber" , mLessonNumber);
                intent.putExtra("QuestionNumber" , mQuestionNumber);
                intent.putExtra("Email" , mEmail);

                startActivity(intent);
            }
        });

    }

    @Override
    public void activateUpVote(int pos) {
        View view = getViewByPosition(pos);
        ((ImageView)view.findViewById(R.id.AnswerListItem_UpVote_ImageView)).setImageResource(R.drawable.upvoteactivated);

    }

    @Override
    public void activateDownVote(int pos) {
        View view = getViewByPosition(pos);
        ((ImageView)view.findViewById(R.id.AnswerListItem_DownVote_ImageView)).setImageResource(R.drawable.downvoteactivated);

    }

    @Override
    public void disableVotes(int pos) {
        View view = getViewByPosition(pos);
        ((ImageView)view.findViewById(R.id.AnswerListItem_UpVote_ImageView)).setImageResource(R.drawable.upvote);
        ((ImageView)view.findViewById(R.id.AnswerListItem_DownVote_ImageView)).setImageResource(R.drawable.downvote);

    }

    @Override
    public void setRate(int rate, int pos) {
        View view = mList.getChildAt(pos -  mList.getFirstVisiblePosition());
        ((TextView)view.findViewById(R.id.AnswerListItem_AnswerRate_TextView)).setText(String.valueOf(rate));
    }

    @Override
    public void fillAnswersList(ArrayList<Answer> answers) {
        AnswerListItemAdapter adapter = new AnswerListItemAdapter(this , answers , mEmail ,mUserType ,this);
        mList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getAnswers(mCourseCode , mLessonNumber , mQuestionNumber , mEmail);
    }

    @Override
    public View.OnClickListener UpVoteClickListener(final int answerNumber, int currentRate, final int position) {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.upVoteButtonClicked(position , mCourseCode , mLessonNumber ,mQuestionNumber,answerNumber ,mEmail );
            }
        };
        return clickListener;
    }

    @Override
    public View.OnClickListener DownVoteClickListener(final int answerNumber, int currentRate, final int position) {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.downVoteButtonClicked(position , mCourseCode , mLessonNumber ,mQuestionNumber,answerNumber ,mEmail );

            }
        };
        return clickListener;
    }

    private View getViewByPosition(int pos) {
        final int firstListItemPosition = mList.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + mList.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return mList.getAdapter().getView(pos , null, mList);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return mList.getChildAt(childIndex);
        }
    }
}
