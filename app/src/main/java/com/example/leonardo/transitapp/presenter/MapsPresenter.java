package com.example.leonardo.transitapp.presenter;

import android.graphics.Color;
import android.util.Log;

import com.example.leonardo.transitapp.R;
import com.example.leonardo.transitapp.interfaces.presenters.IMapsPresenter;
import com.example.leonardo.transitapp.interfaces.views.IMapsView;
import com.example.leonardo.transitapp.model.Segment;
import com.example.leonardo.transitapp.services.RouteService;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.List;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by leonardo on 24/10/17.
 */

public class MapsPresenter implements IMapsPresenter {

    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final float POLYGONE_WIDTH = 10;
    private static final int PADDING_BOUNDS = 30;

    private IMapsView view;
    private RouteService routeService;
    private LatLngBounds.Builder zoomBuilder = new LatLngBounds.Builder();
    private LatLngBounds tmpBounds;

    private CompositeSubscription compositeSubscription;
    public MapsPresenter(IMapsView mapsView) {
        this.view = mapsView;
        this.routeService = new RouteService();
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void loadStartEndPoints(GoogleMap googleMap, List<Segment> segmentList) {

        int startPosition = 0;
        int finishPosition = segmentList.size()-1;
        boolean isFinishMonorute = false;       //case of just one segment

             //when is one segment enter to the method just once
            showMarker(googleMap, startPosition, segmentList, isFinishMonorute);

        if(startPosition == finishPosition) {
            isFinishMonorute = true;        //when is one segment to not overwrite the start marker (to improve)
        }
            showMarker(googleMap, finishPosition, segmentList, isFinishMonorute);
    }

        //custom show marker
    public void showMarker(GoogleMap googleMap, int position, List<Segment> segmentList, boolean isFinishMonorute){

        LatLng latLng = null;
        int markerIconRef = 0;
        Segment segment = segmentList.get(position);
        List<LatLng> decodedPolylines;
        decodedPolylines = getDecodedPolyline(segment);
        String markerText = null;
        LatLng finalDecodedPolyline;

        if(decodedPolylines != null) {      //avoid nullPointerException, shows map without that marker

            finalDecodedPolyline = decodedPolylines.get(decodedPolylines.size() - 1);
            if (position == 0 && decodedPolylines.get(position) != null && !isFinishMonorute) {
                latLng = decodedPolylines.get(position);
                markerText = "Start";
                markerIconRef = R.mipmap.im_map_start;

            } else if (finalDecodedPolyline != null) {
                latLng = decodedPolylines.get(decodedPolylines.size() - 1);
                markerText = "End";
                markerIconRef = R.mipmap.im_map_end;
            }

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(markerText);
            markerOptions.alpha(0.8f);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(markerIconRef));

            googleMap.addMarker(markerOptions);
        }
    }

    public List<LatLng> getDecodedPolyline (Segment segment){

        List<LatLng> decodedPolylines;

        String polyline = segment.getPolyline();

        if (polyline != null){      //avoid nullPointerException
            decodedPolylines = PolyUtil.decode(polyline);

        }else{
            decodedPolylines = null;
        }
        return decodedPolylines;
    }

    public int getColor (Segment segment){
        int color;

        String strColor = segment.getColor();
        if (strColor != null) {         //avoid NullPointerException
            color = Color.parseColor(strColor);

        }else{      //if there is polyline but color = null, paints a white line and log
            color = COLOR_WHITE_ARGB;
            Log.i("error", "no color defined for polyline: " + segment.getPolyline());
        }
        return color;
    }

    @Override
    public void loadPolylines(GoogleMap googleMap, List<Segment> segmentList) {

        List <LatLng> decodedPolylines;
        int color;

        for(int i = 0; i < segmentList.size(); i++) {
            final Segment segment = segmentList.get(i);
            decodedPolylines = getDecodedPolyline(segment);
            color = getColor(segment);
            if(decodedPolylines != null) {
                drawSubPolyline(googleMap, decodedPolylines, color);
            }
        }
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        CameraUpdate cuf = CameraUpdateFactory.newLatLngBounds(tmpBounds, PADDING_BOUNDS);
        googleMap.moveCamera(cuf);
    }

        //paints each segment of the route on the map
    private void drawSubPolyline(GoogleMap googleMap, List<LatLng> subPolyline, int color) {

        if(subPolyline.size() != 0 || subPolyline != null) {


            LatLng startPoint = subPolyline.get(0);

            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.add(startPoint);
            polylineOptions.color(color);
            polylineOptions.geodesic(false);
            polylineOptions.width(POLYGONE_WIDTH);
            polylineOptions.visible(true);

            for (int i = 0; i < subPolyline.size(); i++) {

                if (subPolyline.get(i) != null) {
                    polylineOptions.add(subPolyline.get(i));
                    zoomBuilder.include(subPolyline.get(i));
                    tmpBounds = zoomBuilder.build();
                }
            }
            Polyline polyline = googleMap.addPolyline(polylineOptions);
            polyline.setJointType(JointType.BEVEL);
        }
    }

   @Override
    public void onDestroy() {
    }
}
