package com.example.leonardo.transitapp.model.events;
import com.example.leonardo.transitapp.model.Route;

/**
 * Created by leonardo on 24/10/17.
 */

public class RouteEvent {

    public final Route route;

    public RouteEvent(Route Route) {
        this.route = Route;
    }
}
