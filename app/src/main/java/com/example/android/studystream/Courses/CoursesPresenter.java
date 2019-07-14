package com.example.android.studystream.Courses;

import com.example.android.studystream.Courses.Data.Services.CoursesLocalServicesImpl;
import com.example.android.studystream.Courses.Data.Models.Course;

import java.util.ArrayList;

public class CoursesPresenter implements CoursesContract.Presenter {
   private CoursesContract.View       mView;
   private CoursesLocalServicesImpl   mModel;

    public CoursesPresenter(CoursesContract.View mView) {
        this.mView = mView;
        mModel = new CoursesLocalServicesImpl();
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
