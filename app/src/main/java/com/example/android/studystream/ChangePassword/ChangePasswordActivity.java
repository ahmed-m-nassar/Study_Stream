package com.example.android.studystream.ChangePassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.R;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordContract.View {

    private ChangePasswordPresenter  mPresenter;
    private EditText                 mOldPassword;
    private EditText                 mNewPassword;
    private EditText                 mConfirmPassword;
    private Button                   mSave;
    private String                   mEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        //initializing variables
        /////////////////////////////////////////////////////////////////////////

        //presenter
        mPresenter = new ChangePasswordPresenter(this);

        //views
        mOldPassword = (EditText)findViewById(R.id.ChangePassword_OldPass_EditText);
        mNewPassword = (EditText)findViewById(R.id.ChangePassword_NewPass_EditText);
        mConfirmPassword = (EditText)findViewById(R.id.ChangePassword_ConfirmPassword_EditText);
        mSave   = (Button)findViewById(R.id.ChangePassword_Save_Button);

        //extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");

        ////////////////////////////////////////////////////////////////////////

        //click listeners
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveButtonClicked(mEmail , mOldPassword.getText().toString() , mNewPassword.getText().toString()
                                            ,mConfirmPassword.getText().toString());
            }
        });
    }

    @Override
    public void finishScreen() {
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
