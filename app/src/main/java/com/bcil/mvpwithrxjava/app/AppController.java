package com.bcil.mvpwithrxjava.app;

import android.app.Application;

import com.bcil.mvpwithrxjava.data.DataManager;
import com.bcil.mvpwithrxjava.data.prefs.SharedPrefsHelper;


public class AppController extends Application {

    private DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(getApplicationContext());
        dataManager = new DataManager(sharedPrefsHelper);
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
