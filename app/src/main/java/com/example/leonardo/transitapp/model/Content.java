package com.example.leonardo.transitapp.model;

/**
 * Created by leonardo on 24/10/17.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content implements Serializable {

    @SerializedName("routes")
    @Expose
    private List<Route> routes = null;
    @SerializedName("provider_attribhjhjutes")
    @Expose
    private List<GenericProvider> genericProvider = null;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<GenericProvider> getgenericProvider() {
        return genericProvider;
    }

    public void setGenericProvider(List<GenericProvider> genericProvider) {
        this.genericProvider = genericProvider;
    }

}
