package com.example.leonardo.transitapp.services;

/**
 * Created by leonardo on 25/10/17.
 */

import com.example.leonardo.transitapp.AppHandler;
import com.example.leonardo.transitapp.model.Content;

import retrofit2.http.GET;
import rx.Observable;

public class RouteService {

    public Observable<Content> getRouteAppsObservable(){
        return  AppHandler.getRetrofit()
                .create(FeedApi.class)
                .getFeedApps();
    }

    private interface FeedApi {

        @GET(AppHandler.TOP_FREE_URL + "data.json")
        Observable<Content> getFeedApps();
    }
}
