package com.example.android.studystream.CourseDetails.Lessons.CourseLessons;

import com.example.android.studystream.CourseDetails.Announcements.Models.Announcement;
import com.example.android.studystream.CourseDetails.Lessons.Models.Lesson;

import java.util.ArrayList;

public interface CourseLessonsContract  {
    interface View {
        void showMessage(String message);

        void fillLessonsList(ArrayList<Lesson> lessons);

        void navigateToNewLessonScreen();

        void navigateToLessonDetailsScreen();
    }
    interface Presenter {
        void getLessonList(int courseCode);

        void addLessonButtonClicked();
    }
}
