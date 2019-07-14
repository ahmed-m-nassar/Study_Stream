package com.example.android.studystream.SignUp.Data;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;
import com.example.android.studystream.Base.DataBase.MyApp;

public class SignUpLocalServicesImpl extends DBHelper implements SignUpLocalServices {
    public SignUpLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public boolean checkExistingUser(String email) {
        //select * from Users where ID = "email"
        String query="Select * from  "+ StudyStreamContract.UserEntry.Table_Name +" where " +
                StudyStreamContract.UserEntry.Column_ID+" =?" ;

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
    public void insertNewUser(String email, String firstName, String lastName, String password, boolean userType) {
        ContentValues content = new ContentValues();
        content.put(StudyStreamContract.UserEntry.Column_ID,email);
        content.put(StudyStreamContract.UserEntry.Column_Password,password);
        content.put(StudyStreamContract.UserEntry.Column_FirstName,firstName);
        content.put(StudyStreamContract.UserEntry.Column_SecondName,lastName);

        insert(StudyStreamContract.UserEntry.Table_Name,content);

        content.clear();

        if(userType == true) {
            content.put(StudyStreamContract.Doctor.Column_ID ,email );
            content.put(StudyStreamContract.Doctor.Column_BIO,"Add your bio here");
            insert(StudyStreamContract.Doctor.Table_Name,content);

        } else {
            content.put(StudyStreamContract.Student.Column_ID ,email);
            insert(StudyStreamContract.Student.Table_Name,content);
        }

    }
}
