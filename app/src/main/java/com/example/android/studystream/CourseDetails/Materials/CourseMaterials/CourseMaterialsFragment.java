package com.example.android.studystream.CourseDetails.Materials.CourseMaterials;

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

import com.example.android.studystream.CourseDetails.Announcements.EditAnnouncement.EditAnnouncementFragment;
import com.example.android.studystream.CourseDetails.Announcements.NewAnnouncement.NewAnnouncementFragment;
import com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Adapter.MaterialListAdapter;
import com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Adapter.MaterialListClickListeners;
import com.example.android.studystream.CourseDetails.Materials.EditMaterial.EditMaterialFragment;
import com.example.android.studystream.CourseDetails.Materials.Models.Material;
import com.example.android.studystream.CourseDetails.Materials.NewMaterial.NewMaterialActivity;
import com.example.android.studystream.CourseDetails.Materials.NewMaterial.NewMaterialFragment;
import com.example.android.studystream.R;

import java.util.ArrayList;

public class CourseMaterialsFragment extends Fragment implements CourseMaterialsContract.View , MaterialListClickListeners {
    private CourseMaterialsPresenter mPresenter;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initializing private variables
        ////////////////////////////////////////////////////////////////////////

        //presenter
        mPresenter = new CourseMaterialsPresenter(this);

        //views
        mMaterialsList = (ListView)view.findViewById(R.id.CourseMaterials_MaterialsList_ListView);
        mAddMaterial = (Button)view.findViewById(R.id.CourseMaterials_AddMaterial_Button);

        //extras
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mEmail = bundle.getString("Email");
            mUserType = bundle.getBoolean("UserType" , false);
            mCourseCode = bundle.getInt("CourseCode");
        }

        ///////////////////////////////////////////////////////////////////////////////

        //click listenerss
        ////////////////////////////////////////////////////////
        mAddMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addMaterialButtonClicked();
            }
        });
        ////////////////////////////////////////////////////////

        adjustLayout();

        mPresenter.getMaterialsList(mCourseCode);

    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillAnnouncementsList(ArrayList<Material> materials) {
        MaterialListAdapter adapter = new MaterialListAdapter(getContext() , materials , mEmail , mUserType ,this);
        mMaterialsList.setAdapter(adapter);
    }

    @Override
    public void navigateToNewMaterialScreen() {
        Bundle bundle = new Bundle();
        bundle.putString("Email", mEmail);
        bundle.putInt("CourseCode" , mCourseCode);

        Fragment fragment = new NewMaterialFragment();
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.CourseDetails_Container_FragmentLayout,
                fragment).addToBackStack(null).commit();
    }

    @Override
    public void navigateToMaterialDetailsScreen() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getMaterialsList(mCourseCode);
    }

    //region private functions
    void adjustLayout() {

        if(mUserType == false) {
            mAddMaterial.setVisibility(View.GONE);
        }
    }

    //endregion

    @Override
    public View.OnClickListener materialItemClicked(final int materialNumber ,final String materialTitle,final String materialContent) {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Email", mEmail);
                bundle.putInt("CourseCode" , mCourseCode);
                bundle.putInt("MaterialNumber" , materialNumber);
                bundle.putString("MaterialTitle" , materialTitle);
                bundle.putString("MaterialContent" , materialContent);

                Fragment fragment = new EditMaterialFragment();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.CourseDetails_Container_FragmentLayout,
                        fragment).addToBackStack(null).commit();
            }
        };
        return clickListener;
    }
}
