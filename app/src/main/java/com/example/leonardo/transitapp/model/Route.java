package com.example.leonardo.transitapp.model;

/**
 * Created by leonardo on 24/10/17.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("segments")
    @Expose
    private List<Segment> segments = null;
    @SerializedName("properties")
    @Expose
    private Properties properties;
    @SerializedName("price")
    @Expose
    private Price price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

}
