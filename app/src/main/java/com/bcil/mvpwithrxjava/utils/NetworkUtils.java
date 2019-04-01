package com.bcil.mvpwithrxjava.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by NG on 21-Sep-2017.
 */

public class NetworkUtils {
    private static NetworkInfo networkInfo;
    public static boolean isNetworkAvailable(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }


}
