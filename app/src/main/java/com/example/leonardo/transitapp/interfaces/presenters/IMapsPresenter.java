package com.example.leonardo.transitapp.interfaces.presenters;

import com.example.leonardo.transitapp.model.Segment;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;

/**
 * Created by leonardo on 24/10/17.
 */

public interface IMapsPresenter {
    void loadPolylines(GoogleMap googleMap, List<Segment> segments);
    void loadStartEndPoints(GoogleMap googleMap, List<Segment> segments);
    void onDestroy();

}
