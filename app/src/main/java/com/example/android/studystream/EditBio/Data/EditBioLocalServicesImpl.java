package com.example.android.studystream.EditBio.Data;

import android.content.ContentValues;
import android.content.Context;

import com.example.android.studystream.Base.DataBase.DBHelper;
import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Base.DataBase.StudyStreamContract;

public class EditBioLocalServicesImpl extends DBHelper implements EditBioLocalServices {
    public EditBioLocalServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public void updateBio(String bio , String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudyStreamContract.Doctor.Column_BIO,bio);
        String[] arguments = {email};
         update(StudyStreamContract.Doctor.Table_Name, contentValues , arguments,
                StudyStreamContract.Doctor.Column_ID + " =? ");
    }
}
