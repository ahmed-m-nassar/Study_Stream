package com.example.android.studystream.ChangePassword;

import com.example.android.studystream.ChangePassword.Data.ChangePasswordLocalService;
import com.example.android.studystream.ChangePassword.Data.ChangePasswordLocalServiceImpl;
import com.example.android.studystream.Utils.TextUtils;

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter {
    private ChangePasswordContract.View     mView;
    private ChangePasswordLocalService      mModel;

    public ChangePasswordPresenter(ChangePasswordContract.View mView) {
        this.mView = mView;
        mModel = new ChangePasswordLocalServiceImpl();
    }

    @Override
    public void saveButtonClicked(String email, String oldPassword, String newPassword, String confirmPassowd) {
        //validating fields
        /////////////////////////////////////////////////////
        if( oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassowd.isEmpty()) {
            mView.showMessage("Please fill all fields");
            return;
        }

        //checking spaces in fields
        if(TextUtils.checkSpaces(oldPassword) || TextUtils.checkSpaces(newPassword)
                || TextUtils.checkSpaces(confirmPassowd)) {
            mView.showMessage("Password shouldnt contain spaces , please remove all spaces");
            return;
        }

        //checking passwords
        if(!newPassword.equals(confirmPassowd)) {
            mView.showMessage("Password and confirm password dont match");
            return;
        }

        //checking old password
        if(!mModel.checkExistingUser(email , oldPassword)) {
            mView.showMessage("old password is incorrect");
            return;
        }
        ///////////////////////////////////////////////////

        //updating password
        mModel.updatePassword(email , newPassword);
        mView.showMessage("Password updated successfully!");
        mView.finishScreen();
    }
}
