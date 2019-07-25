package com.example.android.studystream.Main.Data;

import android.content.Context;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.Main.Model.User;

public class MainLocalServicesImpl extends DBHelper implements MainLocalServices {
    public MainLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public String checkLoggedInUser() {
       String query="Select * from  "+ StudyStreamContract.UserEntry.Table_Name +" where " +
                StudyStreamContract.UserEntry.Column_IsLogged + " = 1" ;
        Cursor cursor = Select(query,null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            String email = cursor.getString(cursor.getColumnIndex(StudyStreamContract.UserEntry.Column_ID));
            return email;
        } else {
            cursor.close();
            return null;
        }
    }

    @Override
    public boolean checkUserType(String email) {
        //select * from Doctor where Email = email
        String query="Select * from "+ StudyStreamContract.Doctor.Table_Name +
                " where "+ StudyStreamContract.Doctor.Column_ID +"=? " ;

        String[] Argument = {email};
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
}
