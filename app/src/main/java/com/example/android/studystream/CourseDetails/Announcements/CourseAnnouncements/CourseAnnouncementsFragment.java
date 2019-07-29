package com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Adapter.AnnouncementListAdapter;
import com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Adapter.AnnouncementListClickListeners;
import com.example.android.studystream.CourseDetails.Announcements.EditAnnouncement.EditAnnouncementFragment;
import com.example.android.studystream.CourseDetails.Announcements.Models.Announcement;
import com.example.android.studystream.CourseDetails.Announcements.NewAnnouncement.NewAnnouncementActivity;
import com.example.android.studystream.CourseDetails.Announcements.NewAnnouncement.NewAnnouncementFragment;
import com.example.android.studystream.R;

import java.util.ArrayList;

public class CourseAnnouncementsFragment extends Fragment implements CourseAnnouncementsContract.View,
                                                                        AnnouncementListClickListeners  {

    private CourseAnnouncementsPresenter mPresenter;
    private ListView                     mAnnouncementsList;
    private Button         mAddAnnouncement;
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
        mAddAnnouncement = (Button) view.findViewById(R.id.CourseAnnouncements_AddAnnouncement_Button);

        //getting bundle extras
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mEmail = bundle.getString("Email");
            mUserType = bundle.getBoolean("UserType" , false);
            mCourseCode = bundle.getInt("CourseCode");
        }

        //getting presenter
        mPresenter = new CourseAnnouncementsPresenter(this);

        ////////////////////////////////////////////////////////////////////

        //setting click listeners
        mAddAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addAnnouncementButtonClicked();
            }
        });

        //adjusting screen according to user type
        adjustScreenToUser();

        //get Announcement list
        mPresenter.getAnnouncementsList(mCourseCode);


    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillAnnouncementsList(ArrayList<Announcement> announcements) {
        AnnouncementListAdapter adapter = new AnnouncementListAdapter(getContext() , announcements ,mEmail , mUserType ,this);
        mAnnouncementsList.setAdapter(adapter);
    }

    @Override
    public void navigateToNewAnnouncementScreen() {
        Bundle bundle = new Bundle();
        bundle.putString("Email", mEmail);
        bundle.putInt("CourseCode" , mCourseCode);
        bundle.putBoolean("UserType" , mUserType);

        Fragment fragment = new NewAnnouncementFragment();
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.CourseDetails_Container_FragmentLayout,
                fragment).addToBackStack(null).commit();
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getAnnouncementsList(mCourseCode);
    }

    //region private functions
    @SuppressLint("RestrictedApi")
    private void adjustScreenToUser() {

        if(mUserType == false) {
            mAddAnnouncement.setVisibility(View.GONE);
        }
    }

    //endregion

    @Override
    public View.OnClickListener announcementClicked(final int announcementNumber, final String announcementTitle, final String announcementContent) {

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Email", mEmail);
                bundle.putInt("CourseCode" , mCourseCode);
                bundle.putInt("AnnouncementNumber" , announcementNumber);
                bundle.putString("AnnouncementTitle" , announcementTitle);
                bundle.putString("AnnouncementContent" , announcementContent);

                Fragment fragment = new EditAnnouncementFragment();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.CourseDetails_Container_FragmentLayout,
                        fragment).addToBackStack(null).commit();
            }
        };
        return clickListener;
    }
}
