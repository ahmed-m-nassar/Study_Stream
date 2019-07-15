package com.example.android.studystream.CourseDetails.Lessons.NewLesson.Data;

import android.database.Cursor;

public interface NewLessonLocalServices {

    void    InsertLesson(int course_code,int lesson_number,String title);

    boolean CheckLesson(String title , int CourseCode);

    int     NewLessonID(int CourseCode);



}
