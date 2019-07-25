package com.example.android.studystream.Answers.AddAnswer;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.Questions.AddQuestion.AddQuestionPresenter;
import com.example.android.studystream.R;

public class AddAnswerActivity extends AppCompatActivity implements AddAnswerContract.View {

    private AddAnswerPresenter   mPresenter;
    private EditText             mContent;
    private Button               mAddAnswer;
    private String               mEmail;
    private int                  mCourseCode;
    private int                  mLessonNumber;
    private int                  mQuestionNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_answer);


        //getting private variables
        ///////////////////////////////////////////////////////////////////

        //initializing presenter
        mPresenter = new AddAnswerPresenter(this);

        //getting views
        mContent = (EditText)findViewById(R.id.AddAnswer_AnswerContent_EditText);
        mAddAnswer = (Button) findViewById(R.id.AddAnswer_Add_Button);

        //getting extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");
        mCourseCode = intent.getIntExtra("CourseCode" , -1);
        mLessonNumber = intent.getIntExtra("LessonNumber" , -1);
        mQuestionNumber = intent.getIntExtra("QuestionNumber" , -1);

        ///////////////////////////////////////////////////////////////////

        //add question button click listener
        mAddAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addAnswerButtonClicked( mContent.getText().toString()
                        ,mCourseCode , mLessonNumber , mQuestionNumber ,mEmail);
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
