package com.example.android.studystream.SignUp;

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
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.android.studystream.CoursesHomePage.CoursesHomePageActivity;
import com.example.android.studystream.R;

public class SignUpFragment extends Fragment implements SignUpContract.View {

    private SignUpPesenter  mPresenter;
    private EditText        mEmail;
    private EditText        mPassword;
    private EditText        mConfirmPassword;
    private EditText        mFirstName;
    private EditText        mLastName;
    private RadioButton     mIsDoctor;
    private RadioButton     mIsStudent;
    private Button          mSignUpButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_sign_up,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setting private variables
        /////////////////////////////////////////////////////////////////////////////////
        mPresenter = new SignUpPesenter(this);
        mEmail = (EditText) view.findViewById(R.id.SignUp_Email_EditText);
        mPassword = (EditText)view. findViewById(R.id.SignUp_Password_EditText);
        mConfirmPassword = (EditText)view. findViewById(R.id.SignUp_ConfirmPassword_EditText);
        mFirstName = (EditText) view.findViewById(R.id.SignUp_FirstName_EditText);
        mLastName = (EditText) view.findViewById(R.id.SignUp_LastName_EditText);
        mIsDoctor = (RadioButton)view.findViewById(R.id.SignUp_Doctor_RadioButton);
        mIsStudent = (RadioButton)view.findViewById(R.id.SignUp_Student_RadioButton);
        mSignUpButton = (Button)view.findViewById(R.id.SignUp_SignUp_Button);
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToCourseScreen(String email, boolean userType) {
        Intent intent = new Intent(getContext() , CoursesHomePageActivity.class);
        intent.putExtra("Email" , email);
        intent.putExtra("UserType" , userType);
        startActivity(intent);

    }

    @Override
    public void finishScreen() {
        getActivity().onBackPressed();
    }
}
