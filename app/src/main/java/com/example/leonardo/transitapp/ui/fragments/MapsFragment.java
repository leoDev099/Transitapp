package com.example.leonardo.transitapp.ui.fragments;

/**
 * Created by leonardo on 29/10/17.
 */

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leonardo.transitapp.R;
import com.example.leonardo.transitapp.interfaces.presenters.IMapsPresenter;
import com.example.leonardo.transitapp.interfaces.views.IMapsView;
import com.example.leonardo.transitapp.model.Route;
import com.example.leonardo.transitapp.model.events.ToolbarEvent;
import com.example.leonardo.transitapp.presenter.MapsPresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.greenrobot.eventbus.EventBus;
import java.io.Serializable;


public class MapsFragment extends Fragment implements OnMapReadyCallback, IMapsView {

    private Route route;
    private IMapsPresenter mapsPresenter;
    MapView mapView;
    private OnFragmentInteractionListener mListener;

    public MapsFragment() {
        // Required empty public constructor
    }

    public static MapsFragment newInstance(Route route) {
        MapsFragment mapsFragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putSerializable("route", (Serializable) route);
        mapsFragment.setArguments(args);

        return mapsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        route = (Route) getArguments().getSerializable("route");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().post(new ToolbarEvent(route.getType()));

        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        mapView = rootView.findViewById(R.id.fragment_view_map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        showPolyline(googleMap);
    }

    @Override
    public void showPolyline(GoogleMap googleMap) {

        mapsPresenter = new MapsPresenter(this);
        mapsPresenter.loadPolylines(googleMap, route.getSegments());
        mapsPresenter.loadStartEndPoints(googleMap, route.getSegments());
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
