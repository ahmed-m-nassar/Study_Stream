package com.example.android.studystream.CourseDetails.Materials.EditMaterial;

import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.R;

public class EditMaterialActivity extends AppCompatActivity implements EditMaterialContract.View {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_material);

        //setting private variables
        ////////////////////////////////////////////////////////////////////

        //presenter
        mPresenter = new EditMaterialPresenter(this);

        //views
        mTitle = (EditText)findViewById(R.id.EditMaterial_Title_EditText);
        mContent = (EditText)findViewById(R.id.EditMaterial_Content_EditText);
        mEdit = (Button)findViewById(R.id.EditMaterial_Edit_Button);
        mDelete = (Button)findViewById(R.id.EditMaterial_Delete_Button);

        //extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");
        mCourseCode = intent.getIntExtra("CourseCode" , -1);
        mMaterialNum = intent.getIntExtra("MaterialNumber" , -1);
        mMaterialOriginalTitle = intent.getStringExtra("MaterialTitle");
        mMaterialOriginalContent = intent.getStringExtra("MaterialContent");
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishScreen() {
        finish();
    }

    //region Private functions
    void fillMaterialData() {
        mContent.setText(mMaterialOriginalContent);
        mTitle.setText(mMaterialOriginalTitle);
    }
    //endregion
}
