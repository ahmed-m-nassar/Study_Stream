package com.example.android.studystream.Questions.LessonQuestions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.studystream.Answers.QuestionAnswers.QuestionAnswersActivity;
import com.example.android.studystream.Questions.AddQuestion.AddQuestionActivity;
import com.example.android.studystream.Questions.LessonQuestions.Adapter.QuestionListItemAdapter;
import com.example.android.studystream.Questions.LessonQuestions.Adapter.QuestionListItemClickListeners;
import com.example.android.studystream.Questions.Models.Question;
import com.example.android.studystream.Questions.Models.QuestionVote;
import com.example.android.studystream.R;

import java.util.ArrayList;

public class LessonQuestionsActivity extends AppCompatActivity implements QuestionListItemClickListeners , LessonQuestionsContract.View{

    private LessonQuestionsPresenter mPresenter;
    private ListView                 mList;
    private Button                   mAddQuestion;
    private String                   mEmail;
    private boolean                  mUserType;
    private int                      mCourseCode;
    private int                      mLessonNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_questions);

        //setting private variables
        /////////////////////////////////////////////////////////////////////

        //presenter
        mPresenter = new LessonQuestionsPresenter(this);

        //views
        mList = (ListView)findViewById(R.id.LessonQuestions_List_ListView);
        mAddQuestion = (Button)findViewById(R.id.LessonQuestions_AddQuestion_Button);

        //extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");
        mCourseCode = intent.getIntExtra("CourseCode" , -1);
        mLessonNumber = intent.getIntExtra("LessonNumber" , -1);
        mUserType = intent.getBooleanExtra("UserType" , false);

        //////////////////////////////////////////////////////////////////////
        mPresenter.getQuestions(mCourseCode , mLessonNumber , mEmail);

        //add question click listener
        mAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , AddQuestionActivity.class);
                intent.putExtra("CourseCode" , mCourseCode);
                intent.putExtra("LessonNumber" , mLessonNumber);
                intent.putExtra("Email" , mEmail);

                startActivity(intent);
            }
        });

    }

    @Override
    public void fillQuestionList(ArrayList<Question> questions) {
        QuestionListItemAdapter adapter = new QuestionListItemAdapter(this , questions , mEmail ,mUserType ,this);
        mList.setAdapter(adapter);
    }

    @Override
    public void activateUpVote(int pos) {
        View view = getViewByPosition(pos);
        ((ImageView)view.findViewById(R.id.QuestionListItem_UpVote_ImageView)).setImageResource(R.drawable.upvoteactivated);


    }

    @Override
    public void activateDownVote(int pos) {
        View view = getViewByPosition(pos);
        ((ImageView)view.findViewById(R.id.QuestionListItem_DownVote_ImageView)).setImageResource(R.drawable.downvoteactivated);

    }

    @Override
    public void disableVotes(int pos) {
        View view = getViewByPosition(pos);
        ((ImageView)view.findViewById(R.id.QuestionListItem_UpVote_ImageView)).setImageResource(R.drawable.upvote);
        ((ImageView)view.findViewById(R.id.QuestionListItem_DownVote_ImageView)).setImageResource(R.drawable.downvote);
    }

    @Override
    public void setRate(int rate , int pos) {
        View view = mList.getChildAt(pos -  mList.getFirstVisiblePosition());
        ((TextView)view.findViewById(R.id.QuestionListItem_Rate_TextView)).setText(String.valueOf(rate));
    }

    @Override
    public View.OnClickListener UpVoteClickListener(final int questionNum, final int currentRate , final int questionPosition) {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.upVoteButtonClicked(questionPosition , mCourseCode , mLessonNumber ,questionNum ,mEmail );
            }
        };
        return clickListener;
    }

    @Override
    public View.OnClickListener DownVoteClickListener(final int questionNum, final int currentRate, final int questionPosition) {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.downVoteButtonClicked(questionPosition , mCourseCode , mLessonNumber ,questionNum ,mEmail );

            }
        };
        return clickListener;
    }


    @Override
    public View.OnClickListener QuestionItemCLickListener(final int questionNum, int currentRate) {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.questionClicked(questionNum);
            }
        };
        return clickListener;
    }



    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getQuestions(mCourseCode , mLessonNumber ,mEmail);
    }

    @Override
    public void navigateToAnswersActivity(int questionNumber) {
        Intent intent = new Intent(this , QuestionAnswersActivity.class);
        intent.putExtra("CourseCode" , mCourseCode);
        intent.putExtra("LessonNumber" , mLessonNumber);
        intent.putExtra("QuestionNumber" ,questionNumber );
        intent.putExtra("Email" , mEmail);

        startActivity(intent);
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
