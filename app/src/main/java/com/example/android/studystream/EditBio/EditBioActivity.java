package com.example.android.studystream.EditBio;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.R;

public class EditBioActivity extends AppCompatActivity implements EditBioContract.View {

    private EditBioPresenter    mPresenter;
    private EditText            mBio;
    private Button              mSaveBio;
    private String              mEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);


        //getting private variables
        ///////////////////////////////////////////////////////////////////

        //initializing presenter
        mPresenter = new EditBioPresenter(this);

        //getting views
        mBio = (EditText)findViewById(R.id.EditBio_bio_EditText);
        mSaveBio = (Button) findViewById(R.id.EditBio_SaveBio_Button);

        //getting extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");

        ///////////////////////////////////////////////////////////////////

        //add question button click listener
        mSaveBio.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                mPresenter.EditBioButtonClicked(mBio.getText().toString() , mEmail);
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
