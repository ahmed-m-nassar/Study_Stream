package com.example.android.studystream.CourseDetails.Lessons.CourseLessons;

import com.example.android.studystream.CourseDetails.Lessons.CourseLessons.Data.CourseLessonsLocalServicesImpl;
import com.example.android.studystream.CourseDetails.Lessons.Models.Lesson;

import java.util.ArrayList;

public class CourseLessonsPresenter implements CourseLessonsContract.Presenter {

    private CourseLessonsContract.View mView;
    private CourseLessonsLocalServicesImpl mModel;

    public CourseLessonsPresenter(CourseLessonsContract.View mView) {
        this.mView = mView;
        mModel = new CourseLessonsLocalServicesImpl();
    }

    @Override
    public void getLessonList(int courseCode) {
        ArrayList<Lesson> lessons = mModel.SelectAllLessons(courseCode);
        mView.fillLessonsList(lessons);
    }

    @Override
    public void addLessonButtonClicked() {
        mView.navigateToNewLessonScreen();
    }
}
