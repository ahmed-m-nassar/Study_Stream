package com.example.android.studystream.CourseDetails.Announcements.NewAnnouncement;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.CourseDetails.Lessons.NewLesson.NewLessonContract;
import com.example.android.studystream.R;

public class NewAnnouncementFragment extends Fragment implements NewAnnouncementContract.View {

    private NewAnnouncementPresenter mPresenter;
    private EditText                 mTitle;
    private EditText                 mContent;
    private Button                   mAddAnnouncement;
    private String                   mEmail;
    private int                      mCourseCode;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_new_announcement, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initializing private variables
        //////////////////////////////////////////////////////////

        //presenter
        mPresenter = new NewAnnouncementPresenter(this);

        //views
        mTitle = (EditText) view.findViewById(R.id.NewAnnouncement_Title_EditText);
        mContent = (EditText)view. findViewById(R.id.NewAnnouncement_Content_EditText);
        mAddAnnouncement = (Button) view.findViewById(R.id.NewAnnouncement_AddAnnouncement_Button);

        //extras
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mEmail = bundle.getString("Email");
            mCourseCode = bundle.getInt("CourseCode");
        }
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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishScreen() {
        getActivity().onBackPressed();
    }
}
