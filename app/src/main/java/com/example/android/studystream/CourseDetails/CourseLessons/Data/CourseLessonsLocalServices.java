package com.example.android.studystream.CourseDetails.CourseLessons.Data;

import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.CourseDetails.CourseLessons.Data.Models.Lesson;

import java.util.ArrayList;

public interface CourseLessonsLocalServices {

    ArrayList<Lesson> SelectAllLessons(int CourseCode);

    void              InsertLesson(int course_code,int lesson_number,String title);

    boolean           CheckLesson(String title , int CourseCode);

    int               NewLessonID(int CourseCode);


}
