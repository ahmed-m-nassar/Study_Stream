package com.example.android.studystream.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.studystream.CoursesHomePage.CoursesHomePageActivity;
import com.example.android.studystream.R;
import com.example.android.studystream.SignIn.SignInActivity;
import com.example.android.studystream.SignUp.SignUpActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter   mPresenter ;
    private Button          mSignInButton;
    private Button          mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing private variables
        ////////////////////////////////////////////////////////
        mSignInButton = (Button)findViewById(R.id.Main_SignIn_Button);
        mSignUpButton = (Button)findViewById(R.id.Main_SignUp_Button);
        mPresenter = new MainPresenter(this);
        ///////////////////////////////////////////////////////

        //click listeners
        /////////////////////////////////////////////////////////
        //sign in button click listener
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.SignInButtonClicked();
            }
        });

        //sign up button click listener
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.SignUpButtonClicked();
            }
        });
        /////////////////////////////////////////////////////////

        //checking if a user is already logged in
        mPresenter.checkLoggedInUser();
    }

    @Override
    public void navigateToSignUpScreen() {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToSignInScreen() {
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);

    }

    @Override
    public void navigateToCoursesScreen(String email, boolean userType) {
        Intent intent = new Intent(this, CoursesHomePageActivity.class);
        intent.putExtra("Email" , email);
        intent.putExtra("UserType" , userType);
        startActivity(intent);
    }

    @Override
    public void finishScreen() {
        finish();
    }
}
