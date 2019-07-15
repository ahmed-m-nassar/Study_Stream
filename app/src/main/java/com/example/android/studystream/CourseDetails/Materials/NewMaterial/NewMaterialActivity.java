package com.example.android.studystream.CourseDetails.Materials.NewMaterial;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.R;

public class NewMaterialActivity extends AppCompatActivity implements NewMaterialContract.View {


    private NewMaterialPresenter     mPresenter;
    private EditText                 mTitle;
    private EditText                 mContent;
    private Button                   mAddMaterial;
    private String                   mEmail;
    private int                      mCourseCode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_material);

        //initializing private variables
        //////////////////////////////////////////////////////////

        //presenter
        mPresenter = new NewMaterialPresenter(this);

        //views
        mTitle = (EditText) findViewById(R.id.NewMaterial_Title_EditText);
        mContent = (EditText) findViewById(R.id.NewMaterial_Content_EditText);
        mAddMaterial = (Button)findViewById(R.id.NewMaterial_AddMaterial_Button);

        //extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");
        mCourseCode = intent.getIntExtra("CourseCode" , -1);

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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishScreen() {
        finish();
    }


}
