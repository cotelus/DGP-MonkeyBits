package com.monkeybit.routability;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public final class DBConnect {

    private static final String serverIP =  "172.20.57.68";
    private static final String folderName =  "RoutabilityBD";

    private DBConnect() {}

    public static void getRoute(Context context, DBConnectInterface responseListener, String routeId) {
        String url = "http://" + serverIP + "/" + folderName + "/getRoute.php?IdRoute=" + routeId;
        getTuple(context, responseListener, url);
    }

    public static void getPlace(Context context, DBConnectInterface responseListener, String placeId) {
        String url = "http://" + serverIP + "/" + folderName + "/getPlace.php?IdPlace=" + placeId;
        getTuple(context, responseListener, url);
    }

    public static void getFavouriteRoutes(Context context, DBConnectInterface responseListener, String userEmail, int firstRouteIndex) {
        String url = "http://" + serverIP + "/" + folderName + "/getFavouriteRoutes.php?Email=" + userEmail + "&StartIndex=" + Integer.toString(firstRouteIndex);
        getTuple(context, responseListener, url);
    }

    public static void getFavoritePlaces(Context context, DBConnectInterface responseListener, String userEmail, int firstRouteIndex) {
        String url = "http://" + serverIP + "/" + folderName + "/getFavouritePlaces.php?Email=" + userEmail + "&StartIndex=" + Integer.toString(firstRouteIndex);
        getTuple(context, responseListener, url);
    }

    public static void addAsFavouriteRoute(Context context, DBConnectInterface responseListener, String userEmail, String routeId) {
        String url = "http://" + serverIP + "/" + folderName + "/getFavouriteRoutes.php?Email=" + userEmail + "&IdRoute"+ routeId;
        getTuple(context, responseListener, url);
    }

    public static void addAsFavouritePlace(Context context, DBConnectInterface responseListener, String userEmail, String placeId) {
        String url = "http://" + serverIP + "/" + folderName + "/getFavouritePlaces.php?Email=" + userEmail + "&IdPlace" + placeId;
        getTuple(context, responseListener, url);
    }

    public static void suggestPlace(Context context, DBConnectInterface responseListener, JSONObject suggestedPlace) throws JSONException {
        if (PlaceToSuggest.isValidJson(suggestedPlace)) {
            String suggestedPlaceUrl = "";
            if (suggestedPlace.has("IdPlace")) {
                suggestedPlaceUrl += "IdPlace=" + suggestedPlace.getString("IdPlace");
                suggestedPlaceUrl += "&MadeBy=" + suggestedPlace.getString("MadeBy");
            } else {
                suggestedPlaceUrl += "MadeBy=" + suggestedPlace.getString("MadeBy");
            }
            suggestedPlaceUrl += "&Name=" + suggestedPlace.getString("Name");
            suggestedPlaceUrl += "&Description=" + suggestedPlace.getString("Description");
            suggestedPlaceUrl += "&Localization=" + suggestedPlace.getString("Localization");
            suggestedPlaceUrl += "&Image=" + suggestedPlace.getString("Image");
            suggestedPlaceUrl += "&Accesiblity=" + suggestedPlace.getString("Accesibility");

            suggestedPlaceUrl = suggestedPlaceUrl.replaceAll(" ", "%20");

            String url = "http://" + serverIP + "/" + folderName + "suggestPlace.php?" + suggestedPlaceUrl;
            addTuple(context, responseListener, url);
        }
    }

    private static void getTuple(Context context, DBConnectInterface responseListener, String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, responseListener);
        requestQueue.add(jsonRequest);
    }

    private static void addTuple(Context context, DBConnectInterface responseListener, String url) {
        //@TODO: añadir secuencia para añadir tuplas a una tabla
    }
}

