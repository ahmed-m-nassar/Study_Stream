package com.example.android.studystream.SignIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.CoursesHomePage.CoursesHomePageActivity;
import com.example.android.studystream.R;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private SignInPresenter mPresenter;
    private EditText        mEmail;
    private EditText        mPassword;
    private Button          mSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //initializing private data
        //////////////////////////////////////////////////////////
        mPresenter = new SignInPresenter(this);
        mEmail     = (EditText)findViewById(R.id.SignIn_Email_EditText);
        mPassword  = (EditText)findViewById(R.id.SignIn_Password_EditText);
        mSignIn    = (Button) findViewById(R.id.SignIn_SignIn_Button);
        //////////////////////////////////////////////////////////

        //setting click listeners
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.signInButtonClicked(mEmail.getText().toString()
                                             , mPassword.getText().toString());
            }
        });
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToCoursesScreen(String email , boolean userType) {
        Intent intent = new Intent(this, CoursesHomePageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("Email" , email);
        intent.putExtra("UserType" , userType);
        startActivity(intent);
        finish();
    }
}
