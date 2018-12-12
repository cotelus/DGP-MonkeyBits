package com.monkeybit.routability;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class RouteToSuggest extends Route {

    public RouteToSuggest(String madeBy, String name, String description, String image) {
        this.madeBy = madeBy;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public RouteToSuggest(JSONObject jsonRoute) {
        //@TODO añadir imagen
        if (isValidJson(jsonRoute)) {
            this.setMadeBy(jsonRoute.optString("MadeBy"));
            this.setName(jsonRoute.optString("Name"));
            this.setDescription(jsonRoute.optString("Description"));
        }
    }

    public static boolean isValidJson(JSONObject jsonRoute) {
        //@TODO añadir imagen
        return jsonRoute.has("MadeBy") && jsonRoute.has("Name")
                && jsonRoute.has("Description");
    }

    public JSONObject toJson() {
        JSONObject jsonRoute = new JSONObject();
        try {
            //@TODO añadir imagen
            jsonRoute.put("MadeBy", this.getMadeBy());
            jsonRoute.put("Name", this.getName());
            jsonRoute.put("Description", this.getDescription());
        } catch (JSONException e) {
            Log.d("DEBUG", "Error al pasar un objeto RouteToSuggest a JSON");
            e.printStackTrace();
        }
        return jsonRoute;
    }
}
