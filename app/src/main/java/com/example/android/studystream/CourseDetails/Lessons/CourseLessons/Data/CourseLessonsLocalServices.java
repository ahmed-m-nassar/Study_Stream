package com.example.android.studystream.CourseDetails.Lessons.CourseLessons.Data;

import com.example.android.studystream.CourseDetails.Lessons.Models.Lesson;

import java.util.ArrayList;

public interface CourseLessonsLocalServices {

    ArrayList<Lesson> SelectAllLessons(int CourseCode);

}
