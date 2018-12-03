package com.monkeybit.routability;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

public final class DBConnect {

    private static final String serverIP =  "172.20.57.68";

    private DBConnect() {}

    public static void getRoute(Context context, DBConnectInterface responseListener, String routeId) {
        String url = "http://" + serverIP + "/RoutabilityBD/getRoute.php?Id=" + routeId;
        getTuple(context, responseListener, url);
    }

    public static void getPlace(Context context, DBConnectInterface responseListener, String placeId) {
        String url = "http://" + serverIP + "/RoutabilityBD/getPlace.php?Id=" + placeId;
        getTuple(context, responseListener, url);
    }

    public static void getFavouriteRoutes(Context context, DBConnectInterface responseListener, String userId, int firstRouteIndex) {
        String url = "http://" + serverIP + "/RoutabilityBD/getFavouriteRoutes.php?Id=" + userId + "&StartIndex=" + Integer.toString(firstRouteIndex);
        getTuple(context, responseListener, url);
    }

    public static void getFavoritePlaces(Context context, DBConnectInterface responseListener, String userId, int firstRouteIndex) {
        String url = "http://" + serverIP + "/RoutabilityBD/getFavouritePlaces.php?Id=" + userId + "&StartIndex=" + Integer.toString(firstRouteIndex);
        getTuple(context, responseListener, url);
    }

    public static void addAsFavouriteRoute(Context context, DBConnectInterface responseListener, String userId, String routeId) {
        String url = "http://" + serverIP + "/RoutabilityBD/getFavouriteRoutes.php?Id=" + userId + "&IdRoute"+ routeId;
        getTuple(context, responseListener, url);
    }

    public static void addAsFavouritePlace(Context context, DBConnectInterface responseListener, String userId, String placeId) {
        String url = "http://" + serverIP + "/RoutabilityBD/getFavouritePlaces.php?Id=" + userId + "&IdPlace" + placeId;
        getTuple(context, responseListener, url);
    }

    private static void getTuple(Context context, DBConnectInterface responseListener, String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, responseListener);
        requestQueue.add(jsonRequest);
    }
}

