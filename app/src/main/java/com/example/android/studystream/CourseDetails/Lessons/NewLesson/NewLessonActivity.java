package com.example.android.studystream.CourseDetails.Lessons.NewLesson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.R;

public class NewLessonActivity extends AppCompatActivity implements NewLessonContract.View {

    private NewLessonPresenter  mPresenter;
    private EditText            mLessonTitle;
    private Button              mAddLesson;
    private String              mEmail;
    private int                 mCourseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lesson);

        //initializing private variables
        ///////////////////////////////////////////////////////////////

        //extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");
        mCourseCode = intent.getIntExtra("CourseCode" , -1);

        //views
        mLessonTitle = (EditText)findViewById(R.id.NewLesson_Title_EditText);
        mAddLesson   = (Button)findViewById(R.id.NewLesson_NewLesson_Button);

        //presenter
        mPresenter = new NewLessonPresenter(this);

        ///////////////////////////////////////////////////////////////

        //setting click listener
        mAddLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addLessonButtonClicked(mLessonTitle.getText().toString() , mCourseCode ,mEmail);
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
