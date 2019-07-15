package com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Data;

import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Data.Models.Announcement;

import java.util.ArrayList;

public class CourseAnnouncementsLocalServicesImpl extends DBHelper implements CourseAnnouncementsLocalServices {

    public CourseAnnouncementsLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public ArrayList<Announcement> SelectAllAnnouncements(int CourseCode) {
        //select * from Announcements where Course_Code = course_code Order by date desc
        String query="Select * from "+ StudyStreamContract.Announcements.Table_Name +
                " where "+ StudyStreamContract.Announcements.Column_Course_Code+" = "
                + CourseCode ;

        Cursor cursor=getReadableDatabase().rawQuery(query,null);

        ArrayList<Announcement> announcements = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int courseCode = cursor.getInt(cursor.getColumnIndex(StudyStreamContract.Announcements.Column_Course_Code));
            String announcementTitle = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Announcements.Column_Title));
            int announcementNum = cursor.getInt(cursor.getColumnIndex(StudyStreamContract.Announcements.Column_Announcement_Num));
            String announcementContent = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Announcements.Column_Content));
            String announcementProfessor = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Announcements.Column_Doc_ID));
            String announcementDate = cursor.getString(cursor.getColumnIndex(StudyStreamContract.Announcements.Column_Date));


            announcements.add(new Announcement(announcementTitle , announcementContent , courseCode ,
                    announcementNum , announcementProfessor , announcementDate));
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        cursor.close();

        return announcements;



    }


}
