package com.example.android.studystream.CourseDetails.Materials.EditMaterial;

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

import com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Adapter.MaterialListClickListeners;
import com.example.android.studystream.R;

public class EditMaterialFragment extends Fragment implements EditMaterialContract.View {

    private EditMaterialPresenter mPresenter;
    private EditText              mTitle;
    private EditText              mContent;
    private Button                mEdit;
    private Button                mDelete;
    private String                mEmail;
    private int                   mCourseCode;
    private int                   mMaterialNum;
    private String                mMaterialOriginalTitle;
    private String                mMaterialOriginalContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_edit_material , container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setting private variables
        ////////////////////////////////////////////////////////////////////

        //presenter
        mPresenter = new EditMaterialPresenter(this);

        //views
        mTitle = (EditText)view.findViewById(R.id.EditMaterial_Title_EditText);
        mContent = (EditText)view.findViewById(R.id.EditMaterial_Content_EditText);
        mEdit = (Button)view.findViewById(R.id.EditMaterial_Edit_Button);
        mDelete = (Button)view.findViewById(R.id.EditMaterial_Delete_Button);

        //extras
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mEmail = bundle.getString("Email");
            mCourseCode = bundle.getInt("CourseCode");
            mMaterialNum = bundle.getInt("MaterialNumber");
            mMaterialOriginalTitle =bundle.getString("MaterialTitle");
            mMaterialOriginalContent = bundle.getString("MaterialContent");
        }
        /////////////////////////////////////////////////////////////////////

        //click listeners
        //setting Click listeners
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deleteButtonClicked(mMaterialNum , mCourseCode);
            }
        });

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.updateButtonClicked(mMaterialNum , mCourseCode ,mTitle.getText().toString() ,
                        mMaterialOriginalTitle , mContent.getText().toString());
            }
        });


        //filling the fields with original material data before editing
        fillMaterialData();

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishScreen() {
        getActivity().onBackPressed();
    }

    //region Private functions
    void fillMaterialData() {
        mContent.setText(mMaterialOriginalContent);
        mTitle.setText(mMaterialOriginalTitle);
    }

}
