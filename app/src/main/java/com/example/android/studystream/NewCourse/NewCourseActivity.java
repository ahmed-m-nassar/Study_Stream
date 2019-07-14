package com.example.android.studystream.NewCourse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.R;

public class NewCourseActivity extends AppCompatActivity implements NewCourseContract.View {
    private String                  mEmail;
    private NewCoursePresenter      mPresenter;
    private EditText                mCourseCode;
    private EditText                mCoursePassword;
    private EditText                mCourseTitle;
    private EditText                mCourseDescription;
    private Button                  mAddCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);

        //initializing private variables
        ////////////////////////////////////////////////////////////////
        //getting extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");

        //presenter
        mPresenter = new NewCoursePresenter(this);

        //views
        mCourseCode = (EditText)findViewById(R.id.NewCourse_CourseCode_EditText);
        mCoursePassword = (EditText)findViewById(R.id.NewCourse_CoursePassword_EditText);
        mCourseTitle = (EditText)findViewById(R.id.NewCourse_CourseTitle_EditText);
        mCourseDescription = (EditText)findViewById(R.id.NewCourse_CourseDescription_EditText);
        mAddCourse = (Button) findViewById(R.id.NewCourse_AddCourse_Button);
        /////////////////////////////////////////////////////////////////

        //setting click listeners
        //////////////////////////////////////////////////////////
        mAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addCourseButtonClicked(mCourseCode.getText().toString() , mCourseTitle.getText().toString()
                                                 ,mCoursePassword.getText().toString() , mCourseDescription.getText().toString()
                                                 ,mEmail);
            }
        });
        /////////////////////////////////////////////////////////////
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
