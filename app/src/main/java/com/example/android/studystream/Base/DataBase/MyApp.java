package com.example.android.studystream.Base.DataBase;

import android.app.Application;
import android.content.Context;

//<!-- TODO: is this good architecture? -->
public class MyApp  extends Application {
    private static Context context;
    public void onCreate() {
        super.onCreate();
        MyApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApp.context;
    }
}
