package com.example.android.studystream.Questions.AddQuestion;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.R;

public class AddQuestionActivity extends AppCompatActivity implements AddQuestionContract.View{

    private AddQuestionPresenter mPresenter;
    private EditText             mTitle;
    private EditText             mContent;
    private Button               mAddQuestion;
    private String               mEmail;
    private int                  mCourseCode;
    private int                  mLessonNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        //getting private variables
        ///////////////////////////////////////////////////////////////////

        //initializing presenter
        mPresenter = new AddQuestionPresenter(this);

        //getting views
        mTitle = (EditText)findViewById(R.id.AddQuestion_QuestionTitle_EditText);
        mContent = (EditText)findViewById(R.id.AddQuestion_QuestionContent_EditText);
        mAddQuestion = (Button) findViewById(R.id.AddQuestion_Add_Button);

        //getting extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");
        mCourseCode = intent.getIntExtra("CourseCode" , -1);
        mLessonNumber = intent.getIntExtra("LessonNumber" , -1);

        ///////////////////////////////////////////////////////////////////

        //add question button click listener
        mAddQuestion.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                mPresenter.addQuestionButtonClicked(mTitle.getText().toString() , mContent.getText().toString()
                                                   ,mCourseCode , mLessonNumber ,mEmail);
            }
        });

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishScreen() {
        finish();
    }
}
