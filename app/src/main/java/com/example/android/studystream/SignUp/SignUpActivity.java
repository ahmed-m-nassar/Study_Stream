package com.example.android.studystream.SignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.android.studystream.CoursesHomePage.CoursesHomePageActivity;
import com.example.android.studystream.R;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    private SignUpPesenter  mPresenter;
    private EditText        mEmail;
    private EditText        mPassword;
    private EditText        mConfirmPassword;
    private EditText        mFirstName;
    private EditText        mLastName;
    private RadioButton     mIsDoctor;
    private RadioButton     mIsStudent;
    private Button          mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //setting private variables
        /////////////////////////////////////////////////////////////////////////////////
        mPresenter = new SignUpPesenter(this);
        mEmail = (EditText) findViewById(R.id.SignUp_Email_EditText);
        mPassword = (EditText) findViewById(R.id.SignUp_Password_EditText);
        mConfirmPassword = (EditText) findViewById(R.id.SignUp_ConfirmPassword_EditText);
        mFirstName = (EditText) findViewById(R.id.SignUp_FirstName_EditText);
        mLastName = (EditText) findViewById(R.id.SignUp_LastName_EditText);
        mIsDoctor = (RadioButton)findViewById(R.id.SignUp_Doctor_RadioButton);
        mIsStudent = (RadioButton)findViewById(R.id.SignUp_Student_RadioButton);
        mSignUpButton = (Button)findViewById(R.id.SignUp_SignUp_Button);
        /////////////////////////////////////////////////////////////////////////////////

        //setting click listeners
        //////////////////////////////////////////////////////////////
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.signUpButtonClicked(mEmail.getText().toString() , mPassword.getText().toString()
                                              ,mConfirmPassword.getText().toString() , mFirstName.getText().toString()
                                              ,mLastName.getText().toString() , mIsStudent.isChecked()
                                              ,mIsDoctor.isChecked());
            }
        });
        /////////////////////////////////////////////////////////////
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToCourseScreen(String email, boolean userType) {
        Intent intent = new Intent(this , CoursesHomePageActivity.class);
        intent.putExtra("Email" , email);
        intent.putExtra("UserType" , userType);
        startActivity(intent);
        finish();
    }
}
