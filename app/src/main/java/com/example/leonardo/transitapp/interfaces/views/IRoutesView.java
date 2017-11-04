package com.example.leonardo.transitapp.interfaces.views;

import com.example.leonardo.transitapp.model.Route;
import java.util.List;

/**
 * Created by leonardo on 24/10/17.
 */

public interface IRoutesView {
    void showListRoutes(List<Route> myroutesList);
    void showMessage(String message);

    void showLoading();
    void hideLoading();

}
