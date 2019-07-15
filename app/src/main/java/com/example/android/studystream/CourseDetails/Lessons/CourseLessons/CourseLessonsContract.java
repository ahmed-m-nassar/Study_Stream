package com.example.android.studystream.CourseDetails.Lessons.CourseLessons;

import com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Data.Models.Announcement;
import com.example.android.studystream.CourseDetails.Lessons.CourseLessons.Data.Models.Lesson;

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
