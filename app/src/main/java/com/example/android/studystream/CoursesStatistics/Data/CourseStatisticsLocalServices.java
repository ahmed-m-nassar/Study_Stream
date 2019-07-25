package com.example.android.studystream.CoursesStatistics.Data;

public interface CourseStatisticsLocalServices  {
    int GetNumberOfLessons(int CourseNum);
    int GetNumberOfAnnouncements(int CourseNum);
    int GetNumberOfMaterials(int CourseNum);
    int GetNumberOfQuestions(int CourseNum);
    int GetNumberOfAnswers(int CourseNum);
    int GetNumberOfStudents(int CourseNum);
}
