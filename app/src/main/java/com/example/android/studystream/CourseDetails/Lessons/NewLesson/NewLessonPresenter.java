package com.example.android.studystream.CourseDetails.Lessons.NewLesson;

import com.example.android.studystream.CourseDetails.Lessons.NewLesson.Data.NewLessonLocalServicesImpl;

public class NewLessonPresenter implements NewLessonContract.Presenter {

    private NewLessonLocalServicesImpl mModel;
    private NewLessonContract.View     mView;

    public NewLessonPresenter(NewLessonContract.View mView) {
        this.mView = mView;
        mModel = new NewLessonLocalServicesImpl();
    }

    @Override
    public void addLessonButtonClicked(String lessonTitle, int courseCode, String professor) {
        //validation
        /////////////////////////////////////////////////////////////

        //checking empty fields
        if(lessonTitle.isEmpty()) {
            mView.showMessage("Please add lesson title");
            return;
        }

        //checking existing title
        if(mModel.CheckLesson(lessonTitle , courseCode)) {
            mView.showMessage("Lesson with the same title exists , please pick another title");
            return;
        }
        /////////////////////////////////////////////////////////////

        //adding lesson
        int id = mModel.NewLessonID(courseCode);
        mModel.InsertLesson(courseCode , id , lessonTitle);
        mView.showMessage("Lesson added successfully");
        mView.finishScreen();

    }
}
