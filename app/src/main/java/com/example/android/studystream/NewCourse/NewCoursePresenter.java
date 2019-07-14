package com.example.android.studystream.NewCourse;

import com.example.android.studystream.NewCourse.Data.NewCourseLocalServices;
import com.example.android.studystream.NewCourse.Data.NewCourseLocalServicesImpl;
import com.example.android.studystream.Utils.TextUtils;

public class NewCoursePresenter implements NewCourseContract.Presenter {

    private NewCourseContract.View mView;
    private NewCourseLocalServices mModel;
    public NewCoursePresenter(NewCourseContract.View view) {
        this.mView = view;
        mModel = new NewCourseLocalServicesImpl();
    }

    @Override
    public void addCourseButtonClicked(String courseCode, String courseTitle, String coursePassword, String courseDescription, String courseProfessor) {
        //validating fields
        /////////////////////////////////////////////////////////////

        //checking empty fields
        if(courseCode.isEmpty() || courseTitle.isEmpty() || coursePassword.isEmpty()
                || courseDescription.isEmpty()) {
            mView.showMessage("Please fill all fields");
            return;
        }

        //checking spaces
        if(TextUtils.checkSpaces(courseCode)  || TextUtils.checkSpaces(coursePassword)) {
            mView.showMessage("Course code and password shouldnt contain spaces");
            return;
        }

        //checking existing course
        if(mModel.checkExistingCourse(courseCode)) {
            mView.showMessage("Course code already exits , please pick another one");
            return;
        }

        /////////////////////////////////////////////////////////////

        //adding new course
        mModel.addNewCourse(courseCode,courseTitle,coursePassword,courseDescription,courseProfessor);

        //ending NewCourse screen and returning to courses list screen
        mView.showMessage("Course added successfully");
        mView.finishScreen();
    }
}
