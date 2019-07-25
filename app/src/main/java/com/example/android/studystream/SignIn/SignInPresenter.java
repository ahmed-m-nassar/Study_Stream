package com.example.android.studystream.SignIn;

import com.example.android.studystream.SignIn.Data.SignInLocalServices;
import com.example.android.studystream.SignIn.Data.SignInLocalServicesImpl;
import com.example.android.studystream.Utils.TextUtils;

public class SignInPresenter implements SignInContract.Presenter {

    private SignInContract.View view;
    private SignInLocalServicesImpl model;

    public SignInPresenter(SignInContract.View view) {
        this.view = view;
        model = new SignInLocalServicesImpl();
    }

    @Override
    public void signInButtonClicked(String email , String password) {

        //validate email and password
        //////////////////////////////////////////////////////////////////////////////

        //checking spaces
        if(TextUtils.checkSpaces(email) || TextUtils.checkSpaces(password) ) {
            view.showMessage("Email and password shouldn`t contain spaces");
            return;
        }

        //checking empty fields
        if(email.isEmpty() || password.isEmpty()) {
            view.showMessage("Please fill all fields");
            return;
        }

        //checking if the user exists
        if(!model.checkExistingUser(email,password)) {
            view.showMessage("Incorrect email or password");
            return;
        }

        ///////////////////////////////////////////////////////////////////////////


        //getting user type
        boolean userType = model.checkDoctorUser(email);
        model.setLoggedInUser(email);
        //moving to a new screen
        view.navigateToCoursesScreen(email,userType);

    }
}
