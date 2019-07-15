package com.example.android.studystream.CourseDetails.Lessons.NewLesson;

public interface NewLessonContract {
    interface View {
        void showMessage(String message);
        void finishScreen();
    }
    interface Presenter {
        void addLessonButtonClicked(String lessonTitle , int courseCode , String professor);
    }
}
