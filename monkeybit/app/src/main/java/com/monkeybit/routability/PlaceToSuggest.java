package com.monkeybit.routability;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class PlaceToSuggest {

    private String idPlace, madeBy, name, description, localization, image, accesibility;

    public PlaceToSuggest(){}

    public PlaceToSuggest(String idPlace, String madeBy, String name, String description, String localization, String image, String accesibility) {
        this.idPlace = idPlace;
        this.madeBy = madeBy;
        this.name = name;
        this.description = description;
        this.localization = localization;
        this.image = image;
        this.accesibility = accesibility;
    }

    public PlaceToSuggest(String madeBy, String name, String description, String localization, String image, String accesibility) {
        this.idPlace = "";
        this.madeBy = madeBy;
        this.name = name;
        this.description = description;
        this.localization = localization;
        this.image = image;
        this.accesibility = accesibility;
    }

    public PlaceToSuggest(JSONObject jsonPlace) {
        if (isValidJson(jsonPlace)) {
            this.setIdPlace(jsonPlace.optString("IdPlace"));
            this.setMadeBy(jsonPlace.optString("MadeBy"));
            this.setName(jsonPlace.optString("Name"));
            this.setDescription(jsonPlace.optString("Description"));
            this.setLocalization(jsonPlace.optString("Localitation"));
            this.setImage(jsonPlace.optString("Image"));
            this.setAccesibility(jsonPlace.optString("Accesiblity"));
        }
    }

    public static boolean isValidJson(JSONObject jsonPlace) {
        return jsonPlace.has("MadeBy")&& jsonPlace.has("Name")
                && jsonPlace.has("Description") && jsonPlace.has("Localitation")
                && jsonPlace.has("Image") && jsonPlace.has("Accesibility");
    }

    public JSONObject toJson() {
        JSONObject jsonPlace = new JSONObject();
        try {
            if (!this.getIdPlace().equals("")) {
                jsonPlace.put("IdPlace", this.getIdPlace());
            }
            jsonPlace.put("MadeBy", this.getMadeBy());
            jsonPlace.put("Name", this.getName());
            jsonPlace.put("Description", this.getDescription());
            jsonPlace.put("Localitation", this.getLocalization());
            jsonPlace.put("Image", this.getImage());
            jsonPlace.put("Accesibility", this.getAccesibility());
        } catch (JSONException e) {
            Log.d("DEBUG", "Error al pasar un objeto Place a JSON");
            e.printStackTrace();
        }
        return jsonPlace;
    }

    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
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

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAccesibility() {
        return accesibility;
    }

    public void setAccesibility(String accesibility) {
        this.accesibility = accesibility;
    }
}

