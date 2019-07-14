package com.example.android.studystream.CoursesHomePage;

import com.example.android.studystream.CoursesHomePage.Data.Services.CoursesHomePageLocalServicesImpl;
import com.example.android.studystream.CoursesHomePage.Data.Models.Course;

import java.util.ArrayList;

public class CoursesHomePagePresenter implements CoursesHomePageContract.Presenter {
   private CoursesHomePageContract.View       mView;
   private CoursesHomePageLocalServicesImpl mModel;

    public CoursesHomePagePresenter(CoursesHomePageContract.View mView) {
        this.mView = mView;
        mModel = new CoursesHomePageLocalServicesImpl();
    }

    @Override
    public void getDoctorData(String email) {
        //getting bio
        String bio = mModel.getDoctorBio(email);
        mView.fillDoctorBio(bio);

        //getting courses
        ArrayList<Course> courses = mModel.getDoctorCourses(email);
        mView.fillCoursesList(courses);
    }

    @Override
    public void getStudentData(String email) {
        //getting courses
        ArrayList<Course> courses = mModel.getStudentCourses(email);
        mView.fillCoursesList(courses);
    }
}
