package com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Adapter.AnnouncementListAdapter;
import com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Data.Models.Announcement;
import com.example.android.studystream.CourseDetails.Announcements.NewAnnouncement.NewAnnouncementActivity;
import com.example.android.studystream.R;

import java.util.ArrayList;

public class CourseAnnouncementsFragment extends Fragment implements CourseAnnouncementsContract.View {

    private CourseAnnouncementsPresenter presenter;
    private ListView                     mAnnouncementsList;
    private Button                       mAddAnnouncemet;
    private String                       mEmail;
    private boolean                      mUserType;
    private int                          mCourseCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_announcements, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initializing private variables
        ///////////////////////////////////////////////////////////////////////////////////////

        //getting child views
        mAnnouncementsList = (ListView) view.findViewById(R.id.CourseAnnouncements_AnnouncementsList_ListView);
        mAddAnnouncemet = (Button) view.findViewById(R.id.CourseAnnouncements_AddAnnouncement_Button);

        //getting bundle extras
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mEmail = bundle.getString("Email");
            mUserType = bundle.getBoolean("UserType" , false);
            mCourseCode = bundle.getInt("CourseCode");
        }

        //getting presenter
        presenter = new CourseAnnouncementsPresenter(this);

        ////////////////////////////////////////////////////////////////////

        //setting click listeners
        mAddAnnouncemet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , NewAnnouncementActivity.class);
                intent.putExtra("Email" , mEmail);
                intent.putExtra("CourseCode" , mCourseCode);

                startActivity(intent);
            }
        });

        //adjusting screen according to user type
        adjustScreenToUser();

        //get Announcement list
        presenter.getAnnouncementsList(mCourseCode);


    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillAnnouncementsList(ArrayList<Announcement> announcements) {
        AnnouncementListAdapter adapter = new AnnouncementListAdapter(getContext() , announcements ,mEmail , mUserType);
        mAnnouncementsList.setAdapter(adapter);
    }

    @Override
    public void navigateToNewAnnouncementScreen() {
        Intent intent = new Intent();
        intent.putExtra("Email" , mEmail);
        intent.putExtra("UserType" , mUserType);
        intent.putExtra("CourseCode" ,mCourseCode);
        startActivity(intent);
    }

    @Override
    public void navigateToAnnouncementDetailsScreen() {
        Intent intent = new Intent();
        intent.putExtra("Email" , mEmail);
        intent.putExtra("UserType" , mUserType);
        intent.putExtra("CourseCode" ,mCourseCode);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getAnnouncementsList(mCourseCode);
    }

    //region private functions
    private void adjustScreenToUser() {

        if(mUserType == false) {
            mAddAnnouncemet.setVisibility(View.GONE);
        }
    }

    //endregion
}
