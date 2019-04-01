package com.bcil.mvpwithrxjava.ui.login;

import com.bcil.mvpwithrxjava.data.model.UserInfo;

public class LoginPresenterImpl implements LoginContract.LoginPresenter,LoginContract.GetLoginIntractor.OnFinishedListener {
    private LoginContract.LoginView loginView;
    private LoginContract.GetLoginIntractor getLoginIntractor;

    LoginPresenterImpl(LoginContract.LoginView loginView, LoginContract.GetLoginIntractor getLoginIntractor) {
        this.loginView = loginView;
        this.getLoginIntractor = getLoginIntractor;
    }

    LoginPresenterImpl(LoginContract.LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void validateLoginFromServer() {
        if(loginView != null){
            loginView.showProgress();
        }
        getLoginIntractor.getLoginInfoData(this);
    }

    @Override
    public void onFinished(UserInfo userInfo) {
        if(loginView!=null){
            loginView.setLoginData(userInfo);
            loginView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(loginView != null){
            loginView.onResponseFailure(t);
            loginView.hideProgress();
        }
    }
}
