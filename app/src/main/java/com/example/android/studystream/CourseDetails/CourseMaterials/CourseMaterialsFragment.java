package com.example.android.studystream.CourseDetails.CourseMaterials;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.studystream.CourseDetails.CourseAnnouncements.CourseAnnouncementsPresenter;
import com.example.android.studystream.R;

public class CourseMaterialsFragment extends Fragment {
    private CourseMaterialsPresenter presenter;
    private ListView                 mMaterialsList;
    private Button                   mAddMaterial;
    private String                   mEmail;
    private boolean                  mUserType;
    private int                      mCourseCode;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_materials, container, false);
    }
}
