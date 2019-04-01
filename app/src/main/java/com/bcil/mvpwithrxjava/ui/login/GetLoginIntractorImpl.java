package com.bcil.mvpwithrxjava.ui.login;


import android.util.Log;
import android.widget.Toast;

import com.bcil.mvpwithrxjava.data.model.UserInfo;
import com.bcil.mvpwithrxjava.data.network.CrmApiInterface;
import com.bcil.mvpwithrxjava.data.network.RetrofitInstance;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class GetLoginIntractorImpl implements LoginContract.GetLoginIntractor {

    private CompositeDisposable disposable;
    private String username;
    private String password;

    GetLoginIntractorImpl(String username, String password, CompositeDisposable disposable) {
        this.username = username;
        this.password = password;
        this.disposable = disposable;
    }

    @Override
    public void getLoginInfoData(final OnFinishedListener onFinishedListener) {
        CrmApiInterface crmApiInterface = RetrofitInstance.getRetrofitInstance().create(CrmApiInterface.class);
        disposable.add(crmApiInterface.getResponseOfLogin(new UserInfo(username, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UserInfo>() {
                                   @Override
                                   public void onSuccess(UserInfo user) {
                                       onFinishedListener.onFinished(user);
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                       onFinishedListener.onFailure(e);
                                   }
                               }
                )
        );
       /* Call<UserInfo> userInfoCall = crmApiInterface.getResponseOfLogin(new UserInfo(username,password));
        userInfoCall.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });*/
    }
}
