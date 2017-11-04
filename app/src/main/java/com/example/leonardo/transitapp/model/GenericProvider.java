package com.example.leonardo.transitapp.model;

/**
 * Created by leonardo on 24/10/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GenericProvider implements Serializable {

    @SerializedName("provider_icon_url")
    @Expose
    private String providerIconUrl;
    @SerializedName("disclaimer")
    @Expose
    private String disclaimer;
    @SerializedName("ios_itunes_url")
    @Expose
    private String iosItunesUrl;
    @SerializedName("ios_app_url")
    @Expose
    private String iosAppUrl;
    @SerializedName("android_package_name")
    @Expose
    private String androidPackageName;
    @SerializedName("display_name")
    @Expose
    private String displayName;

    public String getProviderIconUrl() {
        return providerIconUrl;
    }

    public void setProviderIconUrl(String providerIconUrl) {
        this.providerIconUrl = providerIconUrl;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getIosItunesUrl() {
        return iosItunesUrl;
    }

    public void setIosItunesUrl(String iosItunesUrl) {
        this.iosItunesUrl = iosItunesUrl;
    }

    public String getIosAppUrl() {
        return iosAppUrl;
    }

    public void setIosAppUrl(String iosAppUrl) {
        this.iosAppUrl = iosAppUrl;
    }

    public String getAndroidPackageName() {
        return androidPackageName;
    }

    public void setAndroidPackageName(String androidPackageName) {
        this.androidPackageName = androidPackageName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
