package com.example.leonardo.transitapp.model;

/**
 * Created by leonardo on 24/10/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProviderAttributes implements Serializable {

    @SerializedName("vbb")
    @Expose
    private GenericProvider vbb;
    @SerializedName("drivenow")
    @Expose
    private GenericProvider drivenow;
    @SerializedName("car2go")
    @Expose
    private GenericProvider car2go;
    @SerializedName("google")
    @Expose
    private GenericProvider google;
    @SerializedName("nextbike")
    @Expose
    private GenericProvider nextbike;
    @SerializedName("callabike")
    @Expose
    private GenericProvider callabike;

    public GenericProvider getVbb() {
        return vbb;
    }

    public void setVbb(GenericProvider vbb) {
        this.vbb = vbb;
    }

    public GenericProvider getDrivenow() {
        return drivenow;
    }

    public void setDrivenow(GenericProvider drivenow) {
        this.drivenow = drivenow;
    }

    public GenericProvider getCar2go() {
        return car2go;
    }

    public void setCar2go(GenericProvider car2go) {
        this.car2go = car2go;
    }

    public GenericProvider getGoogle() {
        return google;
    }

    public void setGoogle(GenericProvider google) {
        this.google = google;
    }

    public GenericProvider getNextbike() {
        return nextbike;
    }

    public void setNextbike(GenericProvider nextbike) {
        this.nextbike = nextbike;
    }

    public GenericProvider getCallabike() {
        return callabike;
    }

    public void setCallabike(GenericProvider callabike) {
        this.callabike = callabike;
    }

}
