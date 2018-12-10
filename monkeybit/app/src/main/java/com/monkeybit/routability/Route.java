package com.monkeybit.routability;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Route {

    private String idRoute, email, madeBy, name, description;

    public Route() {}

    public Route(String idRoute, String email, String madeBy, String name, String description, String accesibility) {
        this.idRoute = idRoute;
        this.email = email;
        this.madeBy = madeBy;
        this.name = name;
        this.description = description;
    }

    public Route(String email, String madeBy, String name, String description, String accesibility) {
        this.idRoute = "";
        this.email = email;
        this.madeBy = madeBy;
        this.name = name;
        this.description = description;
    }

    public Route(JSONObject jsonRoute) {
        if (isValidJson(jsonRoute)) {
            this.setIdRoute(jsonRoute.optString("IdRoute"));
            this.setEmail(jsonRoute.optString("Email"));
            this.setMadeBy(jsonRoute.optString("MadeBy"));
            this.setName(jsonRoute.optString("Name"));
            this.setDescription(jsonRoute.optString("Description"));
        }
    }

    public static boolean isValidJson(JSONObject jsonRoute) {
        return jsonRoute.has("Email") && jsonRoute.has("MadeBy")
                && jsonRoute.has("Name") && jsonRoute.has("Description");
    }

    public JSONObject toJson() {
        JSONObject jsonPlace = new JSONObject();
        try {
            if (!this.getIdRoute().equals("")) {
                jsonPlace.put("IdRoute", this.getIdRoute());
            }
            jsonPlace.put("Email", this.getEmail());
            jsonPlace.put("MadeBy", this.getMadeBy());
            jsonPlace.put("Name", this.getName());
            jsonPlace.put("Description", this.getDescription());
        } catch (JSONException e) {
            Log.d("DEBUG", "Error al pasar un objeto Route a JSON");
            e.printStackTrace();
        }
        return jsonPlace;
    }

    public String getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(String idRoute) {
        this.idRoute = idRoute;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
