package com.example.android.studystream.CourseDetails.Announcements.EditAnnouncement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.CourseDetails.Materials.EditMaterial.EditMaterialPresenter;
import com.example.android.studystream.R;

public class EditAnnouncementActivity extends AppCompatActivity implements EditAnnouncementContract.View {


    private EditAnnouncementPresenter mPresenter;
    private EditText              mTitle;
    private EditText              mContent;
    private Button                mEdit;
    private Button                mDelete;
    private String                mEmail;
    private int                   mCourseCode;
    private int                   mAnnouncementNum;
    private String                mAnnouncementOriginalTitle;
    private String                mAnnouncementOriginalContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_announcement);
        //setting private variables
        ////////////////////////////////////////////////////////////////////

        //presenter
        mPresenter = new EditAnnouncementPresenter(this);

        //views
        mTitle = (EditText)findViewById(R.id.EditAnnouncement_Title_EditText);
        mContent = (EditText)findViewById(R.id.EditAnnouncement_Content_EditText);
        mEdit = (Button)findViewById(R.id.EditAnnouncement_Edit_Button);
        mDelete = (Button)findViewById(R.id.EditAnnouncement_Delete_Button);

        //extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");
        mCourseCode = intent.getIntExtra("CourseCode" , -1);
        mAnnouncementNum = intent.getIntExtra("AnnouncementNumber" , -1);
        mAnnouncementOriginalTitle = intent.getStringExtra("AnnouncementTitle");
        mAnnouncementOriginalContent = intent.getStringExtra("AnnouncementContent");
        /////////////////////////////////////////////////////////////////////

        //filling the fields with original material data before editing
        fillAnnouncementData();

        //setting Click listeners
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deleteButtonClicked(mAnnouncementNum , mCourseCode);
            }
        });

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.updateButtonClicked(mAnnouncementNum , mCourseCode ,mTitle.getText().toString() ,
                        mContent.getText().toString() , mAnnouncementOriginalTitle);
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

    private void fillAnnouncementData() {
        mContent.setText(mAnnouncementOriginalContent);
        mTitle.setText(mAnnouncementOriginalTitle);
    }
}
