
package com.example.leonardo.transitapp.ui.fragments;
/**
 * Created by leonardo on 25/10/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.leonardo.transitapp.R;
import com.example.leonardo.transitapp.adapters.RoutesListAdapter;
import com.example.leonardo.transitapp.interfaces.presenters.IRoutesPresenter;
import com.example.leonardo.transitapp.interfaces.views.IRoutesView;
import com.example.leonardo.transitapp.model.Route;
import com.example.leonardo.transitapp.model.events.RouteEvent;
import com.example.leonardo.transitapp.model.events.ToolbarEvent;
import com.example.leonardo.transitapp.presenter.RoutesPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class RoutesFragment extends Fragment implements IRoutesView, RoutesListAdapter.RouteListener {

    private ProgressBar pbList;
    private static final String TAG = "RecyclerViewFragment";

    protected RecyclerView rvRoutesList;
    protected RoutesListAdapter routesListAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    private IRoutesPresenter routesPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String toolbarTitle = getResources().getString(R.string.home_toolbar_title);
        EventBus.getDefault().post(new ToolbarEvent(toolbarTitle));
        View rootView = inflater.inflate(R.layout.fragment_routes, container, false);
        rootView.setTag(TAG);

        rvRoutesList = rootView.findViewById(R.id.rvRoutesList);
        pbList = rootView.findViewById(R.id.pbList);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rvRoutesList.setLayoutManager(mLayoutManager);

        routesListAdapter = new RoutesListAdapter(new ArrayList<Route>(), this);
        rvRoutesList.setAdapter(routesListAdapter);
        initDataSet();

        return rootView;
    }

    private void initDataSet() {
        routesPresenter = new RoutesPresenter(this);
        routesPresenter.loadRoutes();
    }

    @Override
    public void showListRoutes(List<Route> routesList) {
        routesListAdapter.notifyData(routesList);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        pbList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbList.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        routesPresenter.onDestroy();
    }

    @Override
    public void onClickRoute(Route route) {

        EventBus.getDefault().post(new RouteEvent(route));
    }
}