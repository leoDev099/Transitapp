package com.example.leonardo.transitapp.ui.activities;

/**
 * Created by leonardo on 24/10/17.
 */

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.leonardo.transitapp.R;

import com.example.leonardo.transitapp.model.Route;
import com.example.leonardo.transitapp.model.events.RouteEvent;
import com.example.leonardo.transitapp.model.events.ToolbarEvent;
import com.example.leonardo.transitapp.ui.fragments.MapsFragment;
import com.example.leonardo.transitapp.ui.fragments.RoutesFragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class HomeActivity extends AppCompatActivity {

    private FrameLayout flContainer;
    private Toolbar toolbarHome;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        flContainer = findViewById(R.id.flContainer);
        toolbarHome = findViewById(R.id.toolbar_home);
        init();
    }

    void init() {
        EventBus.getDefault().register(this);
        fragmentManager = getSupportFragmentManager();
        setupToolbar();
        addRoutesFragment();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbarHome);
    }

    private void addRoutesFragment() {
        RoutesFragment routesFragment = new RoutesFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.flContainer, routesFragment, null);
        fragmentTransaction.commit();
    }

    private void addMapsFragment(Route route){
        if(isGooglePlayAvailable()) {
            MapsFragment mapsFragment = MapsFragment.newInstance(route);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.flContainer, mapsFragment, null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else{
            Log.i("error", "Google Play services not available");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(RouteEvent event) {
        addMapsFragment(event.route);
    }

    @Subscribe
    public void onEvent(ToolbarEvent event) {
        if (event.name != null) {
            getSupportActionBar().setTitle(event.name);
        }else{
            Log.i("error", "Title Null");
        }
    }
    private boolean isGooglePlayAvailable() {
        int googlePlayServiceCode;
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        googlePlayServiceCode = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (googlePlayServiceCode != ConnectionResult.SUCCESS) {
            solveErrorWhenPossible(googleApiAvailability, googlePlayServiceCode);
            return false;
        }
        return true;
    }

    public void solveErrorWhenPossible(GoogleApiAvailability googleApiAvailability, int googlePlayServiceCode) {

        if (googleApiAvailability.isUserResolvableError(googlePlayServiceCode)) {
            googleApiAvailability.getErrorDialog(this, googlePlayServiceCode, 9000).show();
        } else {
            finish();
        }

    }
}