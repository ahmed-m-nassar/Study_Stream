package com.example.android.studystream.SignUp;

import com.example.android.studystream.SignIn.Data.SignInLocalServices;
import com.example.android.studystream.SignIn.Data.SignInLocalServicesImpl;
import com.example.android.studystream.SignUp.Data.SignUpLocalServicesImpl;
import com.example.android.studystream.Utils.TextUtils;

public class SignUpPesenter implements SignUpContract.Presenter {
    private SignUpContract.View mView;
    private SignUpLocalServicesImpl model;

    public SignUpPesenter(SignUpContract.View mView) {
        this.mView = mView;
        model = new SignUpLocalServicesImpl();
    }

    @Override
    public void signUpButtonClicked(String email , String password , String confirmPassword
                                   ,String firstName , String lastName
                                   ,boolean studentRadio , boolean doctorRadio) {
        //validating fields
        ////////////////////////////////////////////////////////////////////////////
        //checking empty fields
        if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
           firstName.isEmpty() || lastName.isEmpty() || (!studentRadio && !doctorRadio)) {
           mView.showMessage("Please fill all fields");
           return;
        }

        //checking spaces in fields
        if(TextUtils.checkSpaces(email) || TextUtils.checkSpaces(password)
        || TextUtils.checkSpaces(confirmPassword) || TextUtils.checkSpaces(firstName)
        || TextUtils.checkSpaces(lastName)) {
            mView.showMessage("Fields shouldnt contain spaces , please remove all spaces");
            return;
        }

        //checking passwords
        if(!password.equals(confirmPassword)) {
            mView.showMessage("Password and confirm password dont match");
            return;
        }

        //checking if the user already exists
        if(model.checkExistingUser(email)) {
           mView.showMessage("Email already taken , please pick another one");
           return;
        }
        ////////////////////////////////////////////////////////////////////////////

        boolean userType;
        if(doctorRadio == true)
            userType = true;
        else
            userType = false;

        model.insertNewUser(email , firstName , lastName , password , userType);

        mView.navigateToCourseScreen(email , userType);

    }
}
