package com.example.android.studystream.JoinCourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.R;

public class JoinCourseActivity extends AppCompatActivity  implements JoinCourseContract.View{

    private JoinCoursePresenter     mPresenter;
    private String                  mEmail;
    private EditText                mCourseCode;
    private EditText                mCoursePass;
    private Button                  mJoinCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_course);

        //initializing private variables
        /////////////////////////////////////////////////////////

        //getting extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");

        //getting presenter
        mPresenter = new JoinCoursePresenter(this);
        mCourseCode = (EditText) findViewById(R.id.JoinCourse_CourseId_EditText);
        mCoursePass = (EditText) findViewById(R.id.JoinCourse_CoursePassword_EditText);
        mJoinCourse = (Button) findViewById(R.id.JoinCourse_JoinCourse_Button);
        //////////////////////////////////////////////////////////

        //setting click listeners
        mJoinCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.joinButtonClicked(Integer.valueOf(mCourseCode.getText().toString())
                        , mCoursePass.getText().toString() , mEmail);
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
