package com.bcil.mvpwithrxjava.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDto {
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("userLogin")
    @Expose
    private String userLogin;
    @SerializedName("userCode")
    @Expose
    private String userCode;
    @SerializedName("userPassword")
    @Expose
    private Object userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Object getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(Object userPassword) {
        this.userPassword = userPassword;
    }
}
