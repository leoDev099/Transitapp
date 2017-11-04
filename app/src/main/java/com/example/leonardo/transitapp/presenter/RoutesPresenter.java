package com.example.leonardo.transitapp.presenter;

import android.util.Log;

import com.example.leonardo.transitapp.interfaces.presenters.IRoutesPresenter;
import com.example.leonardo.transitapp.interfaces.views.IRoutesView;
import com.example.leonardo.transitapp.model.Content;
import com.example.leonardo.transitapp.services.RouteService;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by leonardo on 24/10/17.
 */

public class RoutesPresenter implements IRoutesPresenter {

    private IRoutesView view;
    private RouteService routeService;

    private CompositeSubscription compositeSubscription;

    private Content content;


    public RoutesPresenter(IRoutesView routesView) {
        this.view = routesView;
        this.routeService = new RouteService();
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void loadRoutes() {

        Subscription subscriptionRoute;

        subscriptionRoute = routeService.getRouteAppsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Content>() {
                    @Override
                    public void onCompleted() {
                        view.showListRoutes(content.getRoutes());
                        view.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("error", e.getMessage());
                    }

                    @Override
                    public void onNext(Content contentRoute) {
                        content = contentRoute;
                    }
                });

        compositeSubscription.add(subscriptionRoute);

    }

    @Override
    public void onDestroy() {
        compositeSubscription.clear();
    }
}
