package com.example.android.studystream.CourseDetails.Announcements.NewAnnouncement.Data;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;

import java.util.Date;

public class NewAnnouncementLocalServicesImpl extends DBHelper implements NewAnnouncementLocalServices {
    public NewAnnouncementLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public void InsertAnnouncement(int course_code, int announcement_number, String title, String Content, Date date, String doc_id) {
        ContentValues contentValues = new ContentValues();

        //mapping values to corresponding columns
        contentValues.put(StudyStreamContract.Announcements.Column_Course_Code,course_code);
        contentValues.put(StudyStreamContract.Announcements.Column_Announcement_Num,announcement_number);
        contentValues.put(StudyStreamContract.Announcements.Column_Title,title);
        contentValues.put(StudyStreamContract.Announcements.Column_Content,Content);
        contentValues.put(StudyStreamContract.Announcements.Column_Date,date.toString());
        contentValues.put(StudyStreamContract.Announcements.Column_Doc_ID,doc_id);

        insert(StudyStreamContract.Announcements.Table_Name,contentValues);

    }

    @Override
    public boolean CheckAnnouncement(String title, int CourseCode) {
        String query = "select * from " + StudyStreamContract.Announcements.Table_Name
                + " where " + StudyStreamContract.Announcements.Column_Title + " = '"
                + title + "'  And " + StudyStreamContract.Announcements.Column_Course_Code
                + " = " + CourseCode;

        Cursor cursor= Select(query,null);
        int  count = cursor.getCount();
        cursor.close();
        if (count >= 1)
            return true;
        else
            return false;

    }

    @Override
    public int NewAnnouncementID(int CourseCode) {
        // Select Max(Announcement_Number) from Announcements
        String query = "select max( " + StudyStreamContract.Announcements.Column_Announcement_Num
                +" ) from " + StudyStreamContract.Announcements.Table_Name + " Where "
                + StudyStreamContract.Announcements.Column_Course_Code + " = ?";
        String[] arguments = {String.valueOf(CourseCode)};

        Cursor cursor = Select(query, arguments);
        cursor.moveToFirst();

        int maxID = cursor.getInt(0);

        cursor.close();

        return maxID + 1;
    }
}
