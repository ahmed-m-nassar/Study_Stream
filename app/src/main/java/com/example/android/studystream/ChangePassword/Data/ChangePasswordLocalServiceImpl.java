package com.example.android.studystream.ChangePassword.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;

public class ChangePasswordLocalServiceImpl extends DBHelper implements ChangePasswordLocalService {
    public ChangePasswordLocalServiceImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public boolean checkExistingUser(String email, String password) {
        //select * from Users where ID = "email" And Password = password
        String query="Select * from  "+ StudyStreamContract.UserEntry.Table_Name +" where " +
                StudyStreamContract.UserEntry.Column_ID+" =? and " + StudyStreamContract.UserEntry.Column_Password +
                " = ?";

        String[] Argument = {email , password};
        Cursor cursor = Select(query,Argument);

        if(cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

    @Override
    public void updatePassword(String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudyStreamContract.UserEntry.Column_Password,Integer.valueOf(password));
        String whereclause= StudyStreamContract.UserEntry.Column_ID+" =? ";
        String[] argument= {email};
        update(StudyStreamContract.UserEntry.Table_Name,contentValues,argument,whereclause);

    }
}
