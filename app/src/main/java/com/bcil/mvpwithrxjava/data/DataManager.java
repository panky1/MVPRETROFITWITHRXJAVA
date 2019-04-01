package com.bcil.mvpwithrxjava.data;


import com.bcil.mvpwithrxjava.data.prefs.SharedPrefsHelper;

public class DataManager {

    private SharedPrefsHelper mSharedPrefsHelper;

    public DataManager(SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void clear() {
        mSharedPrefsHelper.clear();
    }



    public void setUserCode(String userCode){
        mSharedPrefsHelper.putUserCode(userCode);
    }

    public String getUserCode(){
        return mSharedPrefsHelper.getUserCoode();
    }

    public void setUserName(String userName){
        mSharedPrefsHelper.putUserName(userName);
    }

    public String getUserName(){
        return mSharedPrefsHelper.getUserName();
    }
    public void setLoggedIn() {
        mSharedPrefsHelper.setLoggedInMode(true);
    }

    public Boolean getLoggedInMode() {
        return mSharedPrefsHelper.getLoggedInMode();
    }


    public void setSearchState(int state) {
        mSharedPrefsHelper.putSearchState(state);
    }

    public int getSearchState() {
        return mSharedPrefsHelper.getSearchState();
    }

}
