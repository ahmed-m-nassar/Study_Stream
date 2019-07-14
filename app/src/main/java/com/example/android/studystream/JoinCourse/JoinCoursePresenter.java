package com.example.android.studystream.JoinCourse;

import com.example.android.studystream.JoinCourse.Data.JoinCourseLocalServiceImpl;
import com.example.android.studystream.Utils.TextUtils;

public class JoinCoursePresenter implements JoinCourseContract.Presenter {
    private JoinCourseContract.View    mView;
    private JoinCourseLocalServiceImpl mModel;

    public JoinCoursePresenter(JoinCourseContract.View mView) {
        this.mView = mView;
        mModel = new JoinCourseLocalServiceImpl();
    }

    @Override
    public void joinButtonClicked(int courseCode, String coursePass, String email) {
        //validating fields
        ////////////////////////////////////////////////////////////////////////////

        //checking empty fields
        if(String.valueOf(courseCode).isEmpty()  || coursePass.isEmpty()) {
            mView.showMessage("Please fill all fields");
            return;
        }

        //checking spaces
        if(TextUtils.checkSpaces(String.valueOf(courseCode)) || TextUtils.checkSpaces(coursePass) ) {
            mView.showMessage("Course code and password shouldnt contains spaces");
            return;
        }

        //checking existing course with passed email and pass
        if(!mModel.checkExistingCourse(courseCode , coursePass)) {
            mView.showMessage("Code or password is incorrect");
            return;
        }

        //checking if the student already joined the course
        if(mModel.checkJoinedByStudent(email , courseCode)) {
            mView.showMessage("You already joined this course");
            return;
        }
        ////////////////////////////////////////////////////////////////////////

        //joining course
        mModel.joinCourse(email , courseCode);
        mView.finishScreen();
    }
}
