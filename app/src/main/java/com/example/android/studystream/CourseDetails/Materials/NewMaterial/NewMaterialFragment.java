package com.example.android.studystream.CourseDetails.Materials.NewMaterial;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
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

public class NewMaterialFragment extends Fragment implements NewMaterialContract.View {

    private NewMaterialPresenter     mPresenter;
    private EditText                 mTitle;
    private EditText                 mContent;
    private Button                   mAddMaterial;
    private String                   mEmail;
    private int                      mCourseCode;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_new_material, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initializing private variables
        //////////////////////////////////////////////////////////

        //presenter
        mPresenter = new NewMaterialPresenter(this);

        //views
        mTitle = (EditText) view.findViewById(R.id.NewMaterial_Title_EditText);
        mContent = (EditText) view.findViewById(R.id.NewMaterial_Content_EditText);
        mAddMaterial = (Button)view.findViewById(R.id.NewMaterial_AddMaterial_Button);

        //extras
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mEmail = bundle.getString("Email");
            mCourseCode = bundle.getInt("CourseCode");
        }
        ////////////////////////////////////////////////////////////

        //setting click listeners
        mAddMaterial.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                mPresenter.addMaterialButtonClicked(mTitle.getText().toString() , mContent.getText().toString()
                        , mCourseCode , mEmail);
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

}
