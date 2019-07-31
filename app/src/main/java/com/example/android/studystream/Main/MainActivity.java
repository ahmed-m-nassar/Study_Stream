package com.example.android.studystream.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.studystream.CoursesHomePage.CoursesHomePageActivity;
import com.example.android.studystream.Invite.InviteFragment;
import com.example.android.studystream.R;
import com.example.android.studystream.SignIn.SignInFragment;
import com.example.android.studystream.SignUp.SignUpFragment;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter   mPresenter ;
    private Button          mSignInButton;
    private Button          mSignUpButton;
    private TextView        mInvite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing private variables
        ////////////////////////////////////////////////////////
        mSignInButton = (Button)findViewById(R.id.Main_SignIn_Button);
        mSignUpButton = (Button)findViewById(R.id.Main_SignUp_Button);
        mInvite       =(TextView)findViewById(R.id.Main_Invite_TextView);
        mPresenter = new MainPresenter(this);
        ///////////////////////////////////////////////////////

        //click listeners
        /////////////////////////////////////////////////////////
        //sign in button click listener
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_Parent_Layout,
                        new SignInFragment()).addToBackStack(null).commit();
            }
        });

        //sign up button click listener
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_Parent_Layout,
                        new SignUpFragment()).addToBackStack(null).commit();
            }
        });

        //invite your friends click listener
        mInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_Parent_Layout,
                        new InviteFragment()).addToBackStack(null).commit();
            }
        });
        /////////////////////////////////////////////////////////

        //checking if a user is already logged in
        mPresenter.checkLoggedInUser();
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
