package com.bcil.mvpwithrxjava.ui.login;

import com.bcil.mvpwithrxjava.data.model.UserInfo;

public interface LoginContract {

    interface LoginPresenter{

        void onDestroy();
        void validateLoginFromServer();
    }

    interface LoginView{
        void showProgress();
        void hideProgress();
        void setLoginData(UserInfo userInfo);
        void onResponseFailure(Throwable throwable);
    }

    interface GetLoginIntractor {

        interface OnFinishedListener {
            void onFinished(UserInfo userInfo);
            void onFailure(Throwable t);
        }

        void getLoginInfoData(OnFinishedListener onFinishedListener);
    }
}
