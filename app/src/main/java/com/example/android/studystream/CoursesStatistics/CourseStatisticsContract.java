package com.example.android.studystream.CoursesStatistics;

import com.example.android.studystream.CourseDetails.Announcements.Models.Announcement;
import com.example.android.studystream.CoursesStatistics.Model.Statistics;

import java.util.ArrayList;

public interface CourseStatisticsContract {
    interface View {
        void fillCoursesStatistics(ArrayList<Statistics> statistics);
    }
    interface Presenter {
        void getCoursesStatistics(String email);
    }

}
