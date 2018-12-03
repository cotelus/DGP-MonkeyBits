package com.monkeybit.routability;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Place {

    private String IdPlace, email, MadeBy, name, description, localitation, image, accesibility;

    public Place (JSONObject jsonPlace) {
        if (isValidJson(jsonPlace)) {
            this.setIdPlace(jsonPlace.optString("IdPlace"));
            this.setEmail(jsonPlace.optString("Email"));
            this.setMadeBy(jsonPlace.optString("MadeBy"));
            this.setName(jsonPlace.optString("Name"));
            this.setDescription(jsonPlace.optString("Description"));
            this.setLocalitation(jsonPlace.optString("Localitation"));
            this.setImage(jsonPlace.optString("Image"));
            this.setAccesibility(jsonPlace.optString("Accesiblity"));
        }
    }

    private boolean isValidJson(JSONObject jsonPlace) {
        return jsonPlace.has("IdPlace") && jsonPlace.has("Email") && jsonPlace.has("MadeBy")
                && jsonPlace.has("Name") && jsonPlace.has("Description") && jsonPlace.has("Localitation")
                && jsonPlace.has("Image") && jsonPlace.has("Accesibility");
    }

    public JSONObject toJson() {
        JSONObject jsonPlace = new JSONObject();
        try {
            jsonPlace.put("IdPlace", this.getIdPlace());
            jsonPlace.put("Email", this.getEmail());
            jsonPlace.put("MadeBy", this.getMadeBy());
            jsonPlace.put("Name", this.getName());
            jsonPlace.put("Description", this.getDescription());
            jsonPlace.put("Localitation", this.getLocalitation());
            jsonPlace.put("Image", this.getImage());
            jsonPlace.put("Accesibility", this.getAccesibility());
        } catch (JSONException e) {
            Log.d("DEBUG", "Error al pasar un objeto Place a JSON");
            e.printStackTrace();
        }
        return jsonPlace;
    }

    public String getIdPlace() {
        return IdPlace;
    }

    public void setIdPlace(String idPlace) {
        IdPlace = idPlace;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMadeBy() {
        return MadeBy;
    }

    public void setMadeBy(String madeBy) {
        MadeBy = madeBy;
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

    public String getLocalitation() {
        return localitation;
    }

    public void setLocalitation(String localitation) {
        this.localitation = localitation;
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
