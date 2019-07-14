package com.example.android.studystream.SignIn.Data;

import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.BaseLocalServices;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.Base.DataBase.MyApp;

public class SignInLocalServicesImpl extends BaseLocalServices implements SignInLocalServices {
//<!-- TODO: is this a good way to get the app context? -->
    public SignInLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public boolean checkExistingUser(String email, String password) {
        //select * from Users where ID = "email" AND Password = "password"
        String query="Select * from  "+ StudyStreamContract.UserEntry.Table_Name +" where " +
                StudyStreamContract.UserEntry.Column_ID+" =? And " +
                StudyStreamContract.UserEntry.Column_Password+" =?" ;

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
        //<!-- TODO: Should I return Cursor or decompose it to its components and pass it to the presenter? -->

    }


    @Override
    public boolean checkDoctorUser(String email) {
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

    @Override
    public Cursor getUserData(String email) {
        //select * from Users where ID = "email"
        String query="Select * from "+ StudyStreamContract.UserEntry.Table_Name +
                " where "+ StudyStreamContract.UserEntry.Column_ID +" =? " ;

        String[] Argument = {email};
        Cursor cursor = Select(query,Argument);

        cursor.moveToFirst();
        return cursor;
    }
}
