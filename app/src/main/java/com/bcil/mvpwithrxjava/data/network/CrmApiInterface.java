package com.bcil.mvpwithrxjava.data.network;

import com.bcil.mvpwithrxjava.data.model.UserInfo;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CrmApiInterface {
    @Headers("Content-Type: application/json")
    @POST("api/user/login")
    Single<UserInfo> getResponseOfLogin(@Body UserInfo jsonObject);

}
