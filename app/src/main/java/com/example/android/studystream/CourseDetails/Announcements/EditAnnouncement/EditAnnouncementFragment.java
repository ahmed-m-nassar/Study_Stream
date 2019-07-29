package com.example.android.studystream.CourseDetails.Announcements.EditAnnouncement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.R;

public class EditAnnouncementFragment extends Fragment implements EditAnnouncementContract.View {
    private EditAnnouncementPresenter mPresenter;
    private EditText mTitle;
    private EditText              mContent;
    private Button mEdit;
    private Button                mDelete;
    private String                mEmail;
    private int                   mCourseCode;
    private int                   mAnnouncementNum;
    private String                mAnnouncementOriginalTitle;
    private String                mAnnouncementOriginalContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_edit_announcement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setting private variables
        ////////////////////////////////////////////////////////////////////

        //presenter
        mPresenter = new EditAnnouncementPresenter(this);

        //views
        mTitle = (EditText)view.findViewById(R.id.EditAnnouncement_Title_EditText);
        mContent = (EditText)view.findViewById(R.id.EditAnnouncement_Content_EditText);
        mEdit = (Button)view.findViewById(R.id.EditAnnouncement_Edit_Button);
        mDelete = (Button)view.findViewById(R.id.EditAnnouncement_Delete_Button);

        //extras
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mEmail = bundle.getString("Email");
            mCourseCode = bundle.getInt("CourseCode");
            mAnnouncementNum = bundle.getInt("AnnouncementNumber");
            mAnnouncementOriginalTitle =bundle.getString("AnnouncementTitle");
            mAnnouncementOriginalContent = bundle.getString("AnnouncementContent");
        }

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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishScreen() {
        getActivity().onBackPressed();
    }

    private void fillAnnouncementData() {
        mContent.setText(mAnnouncementOriginalContent);
        mTitle.setText(mAnnouncementOriginalTitle);
    }
}
