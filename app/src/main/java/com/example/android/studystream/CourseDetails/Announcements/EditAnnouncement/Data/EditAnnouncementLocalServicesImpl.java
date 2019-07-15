package com.example.android.studystream.CourseDetails.Announcements.EditAnnouncement.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;

public class EditAnnouncementLocalServicesImpl extends DBHelper implements EditAnnouncementLocalServices {

    public EditAnnouncementLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public boolean CheckAnnouncement(String title, int CourseCode) {
        String query = "select * from " + StudyStreamContract.Announcements.Table_Name
                + " where " + StudyStreamContract.Announcements.Column_Title +
                " = '" + title + "'  And " + StudyStreamContract.Announcements.Column_Course_Code
                + " = " + CourseCode;

        Cursor mCount= Select(query,null);
        int  count = mCount.getCount();
        mCount.close();
        if (count >= 1)
            return true;
        else
            return false;
    }

    @Override
    public void DeleteAnnouncement(int courseCode, int matNum) {
        String whereclause = StudyStreamContract.Announcements.Column_Course_Code + " =? AND "
                + StudyStreamContract.Announcements.Column_Announcement_Num + " =?";
        String[] arguments = {String.valueOf(courseCode),String.valueOf(matNum)};
        deleteWithoutCascade(StudyStreamContract.Announcements.Table_Name,arguments,whereclause);
    }

    @Override
    public void UpdateAnnouncement(int courseCode, int annNum, String title, String content) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudyStreamContract.Announcements.Column_Title,title);
        contentValues.put(StudyStreamContract.Announcements.Column_Content,content);

        String whereclause = StudyStreamContract.Announcements.Column_Course_Code + " =? AND "
                + StudyStreamContract.Announcements.Column_Announcement_Num + " =?";
        String[] arguments = {String.valueOf(courseCode),String.valueOf(annNum)};
        update(StudyStreamContract.Announcements.Table_Name,contentValues,arguments,whereclause);
    }
}
