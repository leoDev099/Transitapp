package com.example.leonardo.transitapp.model;

/**
 * Created by leonardo on 24/10/17.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Segment implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("num_stops")
    @Expose
    private Integer numStops;
    @SerializedName("stops")
    @Expose
    private List<Stop> stops = null;
    @SerializedName("travel_mode")
    @Expose
    private String travelMode;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("icon_url")
    @Expose
    private String iconUrl;
    @SerializedName("polyline")
    @Expose
    private String polyline;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumStops() {
        return numStops;
    }

    public void setNumStops(Integer numStops) {
        this.numStops = numStops;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

}
