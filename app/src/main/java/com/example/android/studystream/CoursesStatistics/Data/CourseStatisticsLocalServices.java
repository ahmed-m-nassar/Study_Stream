package com.example.android.studystream.CoursesStatistics.Data;

import java.util.ArrayList;

public interface CourseStatisticsLocalServices  {
    ArrayList<Integer> getCoursesIdByDoctor(String email);
    int GetNumberOfLessons(int CourseNum);
    int GetNumberOfAnnouncements(int CourseNum);
    int GetNumberOfMaterials(int CourseNum);
    int GetNumberOfQuestions(int CourseNum);
    int GetNumberOfAnswers(int CourseNum);
    int GetNumberOfStudents(int CourseNum);
}
