package com.example.android.studystream.Main;

import com.example.android.studystream.Main.Data.MainLocalServicesImpl;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View     mView ;
    private MainLocalServicesImpl mModel;

    public MainPresenter(MainContract.View view) {
        this.mView = view;
        mModel = new MainLocalServicesImpl();
    }

    @Override
    public void SignUpButtonClicked() {
        mView.navigateToSignUpScreen();
    }

    @Override
    public void SignInButtonClicked() {
        mView.navigateToSignInScreen();
    }

    @Override
    public void checkLoggedInUser() {
        String email = mModel.checkLoggedInUser();

        if(email != null) {
            boolean userType = mModel.checkUserType(email);
            mView.navigateToCoursesScreen(email , userType);
            mView.finishScreen();
        }
    }
}
