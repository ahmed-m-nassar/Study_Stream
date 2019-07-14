package com.example.android.studystream.CoursesHomePage.Data.Services;

import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.CoursesHomePage.Data.Models.Course;
import com.example.android.studystream.Base.DataBase.MyApp;

import java.util.ArrayList;

public class CoursesHomePageLocalServicesImpl extends DBHelper implements CoursesHomePageLocalServices {

    public CoursesHomePageLocalServicesImpl() {
        super(MyApp.getAppContext());
    }


    @Override
    public String getDoctorBio(String email ) {
        //select Bio From Doctor where id = "id"
        String query = "Select " + StudyStreamContract.Doctor.Column_BIO + " From " +
                StudyStreamContract.Doctor.Table_Name + " Where " + StudyStreamContract.Doctor.Column_ID + " =? ";
        String[] Argument = {email};
        Cursor cursor = Select(query,Argument);
        cursor.moveToFirst();

        return cursor.getString(0);


    }

    @Override
    public ArrayList<Course> getDoctorCourses(String email) {
        String query ="Select * from "+ StudyStreamContract.Course.Table_Name +
                " where " + StudyStreamContract.Course.Column_Doc_ID + " =? ";
        String[] Argument = {email};
        Cursor cursor = Select(query,Argument);

        //<!-- TODO: should I get course data from cursor here  or in presenter? -->
        //extracting courses
        /////////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Course> courses = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int courseCode = cursor.getInt(cursor.getColumnIndex(StudyStreamContract.Course.Column_Code));
            String coursetitle=cursor.getString(cursor.getColumnIndex(StudyStreamContract.Course.Column_Title));
            String courseDescription=cursor.getString(cursor.getColumnIndex(StudyStreamContract.Course.Column_Description));
            String coursePass=cursor.getString(cursor.getColumnIndex(StudyStreamContract.Course.Column_Password));
            String courseProfessor=cursor.getString(cursor.getColumnIndex(StudyStreamContract.Course.Column_Doc_ID));

            courses.add(new Course(coursetitle , courseCode , coursePass , courseDescription , courseProfessor));
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        cursor.close();

        return courses;
    }

    @Override
    public ArrayList<Course> getStudentCourses(String email) {
        //select * from Student,Course where Stud_Course.id = email AND StudCourse.CourseCode = Courses.Code
        String query ="Select * From "+ StudyStreamContract.STUD_COURSE.Table_Name+" , " +
                StudyStreamContract.Course.Table_Name+" Where " + StudyStreamContract.STUD_COURSE.Column_Stud_ID +
                " =? and "+ StudyStreamContract.Course.Column_Code+" = "+ StudyStreamContract.STUD_COURSE.Column_Course_Code ;
        String[] Argument = {email};
        Cursor cursor = Select(query,Argument);

        //extracting courses
        /////////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Course> courses = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int courseCode = cursor.getInt(cursor.getColumnIndex(StudyStreamContract.Course.Column_Code));
            String coursetitle=cursor.getString(cursor.getColumnIndex(StudyStreamContract.Course.Column_Title));
            String courseDescription=cursor.getString(cursor.getColumnIndex(StudyStreamContract.Course.Column_Description));
            String coursePass=cursor.getString(cursor.getColumnIndex(StudyStreamContract.Course.Column_Password));
            String courseProfessor=cursor.getString(cursor.getColumnIndex(StudyStreamContract.Course.Column_Doc_ID));

            courses.add(new Course(coursetitle , courseCode , coursePass , courseDescription , courseProfessor));
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        cursor.close();

        return courses;
    }
}
