package com.example.android.studystream.SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.CoursesHomePage.CoursesHomePageActivity;
import com.example.android.studystream.R;

public class SignInFragment extends Fragment implements SignInContract.View {

    private SignInPresenter mPresenter;
    private EditText        mEmail;
    private EditText        mPassword;
    private Button          mSignIn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_sign_in , container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initializing private data
        //////////////////////////////////////////////////////////
        mPresenter = new SignInPresenter(this);
        mEmail     = (EditText)view.findViewById(R.id.SignIn_Email_EditText);
        mPassword  = (EditText)view.findViewById(R.id.SignIn_Password_EditText);
        mSignIn    = (Button)view.findViewById(R.id.SignIn_SignIn_Button);
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
        Toast.makeText(getActivity() , message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToCoursesScreen(String email , boolean userType) {

        Intent intent = new Intent(getContext(), CoursesHomePageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("Email" , email);
        intent.putExtra("UserType" , userType);
        startActivity(intent);



    }
}
