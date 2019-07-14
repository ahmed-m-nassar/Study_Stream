package com.example.android.studystream.NewAnnouncement;

import android.annotation.TargetApi;
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

public class NewAnnouncementActivity extends AppCompatActivity implements NewAnnouncementContract.View {


    private NewAnnouncementPresenter mPresenter;
    private EditText                 mTitle;
    private EditText                 mContent;
    private Button                   mAddAnnouncement;
    private String                   mEmail;
    private int                      mCourseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_announcement);

        //initializing private variables
        //////////////////////////////////////////////////////////

        //presenter
        mPresenter = new NewAnnouncementPresenter(this);

        //views
        mTitle = (EditText) findViewById(R.id.NewAnnouncement_Title_EditText);
        mContent = (EditText) findViewById(R.id.NewAnnouncement_Content_EditText);
        mAddAnnouncement = (Button)findViewById(R.id.NewAnnouncement_AddAnnouncement_Button);

        //extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");
        mCourseCode = intent.getIntExtra("CourseCode" , -1);

        ////////////////////////////////////////////////////////////

        //setting click listeners
        mAddAnnouncement.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                mPresenter.addAnnouncementButtonClicked(mTitle.getText().toString() , mContent.getText().toString()
                        , mCourseCode , mEmail);
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
