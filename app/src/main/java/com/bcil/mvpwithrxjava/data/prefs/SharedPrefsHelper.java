package com.bcil.mvpwithrxjava.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;


import com.bcil.mvpwithrxjava.utils.AppConstants;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefsHelper {

    private static final String MY_PREFS = "CRM_PREFS";
    private static final String EMAIL = "EMAIL";
    private static final String USERCODE = "USERCODE";
    private static final String USERNAME = "USERNAME";
    private static final String SEARCHSTATE = "SEARCHSTATE";
    private static final String IS_LOG_IN = "IS_LOGGED_IN";

    private SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }


    public boolean getLoggedInMode() {
        return mSharedPreferences.getBoolean(IS_LOG_IN, false);
    }

    public void setLoggedInMode(boolean loggedIn) {
        mSharedPreferences.edit().putBoolean(IS_LOG_IN, loggedIn).apply();
    }

    public void putUserCode(String userCode) {
        mSharedPreferences.edit().putString(USERCODE, userCode).apply();
    }

    public String getUserCoode() {
        return mSharedPreferences.getString(USERCODE, AppConstants.EMPTY);
    }


    public void putUserName(String userName) {
        mSharedPreferences.edit().putString(USERNAME, userName).apply();
    }

    public String getUserName() {
        return  mSharedPreferences.getString(USERNAME, AppConstants.EMPTY);
    }

    public void putSearchState(int searchState) {
        mSharedPreferences.edit().putInt(SEARCHSTATE, searchState).apply();
    }

    public int getSearchState() {
        return  mSharedPreferences.getInt(SEARCHSTATE, 0);
    }
}

