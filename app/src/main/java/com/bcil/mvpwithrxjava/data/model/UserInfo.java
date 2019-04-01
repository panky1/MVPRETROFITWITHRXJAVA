package com.bcil.mvpwithrxjava.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("user_dto")
    @Expose
    private UserDto userDto;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("userLogin")
    @Expose
    private String userLogin;
    @SerializedName("userPassword")
    @Expose
    private String userPassword;

    public UserInfo() {
    }

    public UserInfo(String userLogin, String userPassword) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
