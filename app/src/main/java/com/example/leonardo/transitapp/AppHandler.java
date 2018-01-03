package com.example.leonardo.transitapp;

/**
 * Created by leonardo on 24/10/17.
 */

import android.app.Application;
import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppHandler extends Application {

    public static final String SERVER_URL = "https://raw.githubusercontent.com/";
    public static final String TOP_FREE_URL = "leoDev099/Transitapp/master/";

    private static Context context;
    private static AppHandler instance;
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
    }

    public static AppHandler getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(SERVER_URL)
                    .build();
        }
        return retrofit;
    }
}
