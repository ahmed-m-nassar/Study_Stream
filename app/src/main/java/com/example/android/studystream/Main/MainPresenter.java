package com.example.android.studystream.Main;

public class MainPresenter implements MainContract.Presenter {

    MainContract.View view ;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void SignUpButtonClicked() {
        view.navigateToSignUpScreen();
    }

    @Override
    public void SignInButtonClicked() {
        view.navigateToSignInScreen();
    }
}
