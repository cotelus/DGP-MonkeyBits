package com.monkeybit.routability;

import org.json.JSONObject;

public class Place {

    private String IdPlace, email, MadeBy, name, description, localitation, image, accesibility;

    public Place (JSONObject jsonPlace) {
        assert (!isValidJson(jsonPlace));
        this.setIdPlace(jsonPlace.optString("IdPlace"));
        this.setEmail(jsonPlace.optString("Email"));
        this.setMadeBy(jsonPlace.optString("MadeBy"));
        this.setName(jsonPlace.optString("Name"));
        this.setDescription(jsonPlace.optString("Description"));
        this.setLocalitation(jsonPlace.optString("Localitation"));
        this.setImage(jsonPlace.optString("Image"));
        this.setAccesibility(jsonPlace.optString("Accesiblity"));
    }

    private boolean isValidJson(JSONObject jsonPlace) {
        return jsonPlace.has("IdPlace") && jsonPlace.has("Email") && jsonPlace.has("MadeBy")
                && jsonPlace.has("Name") && jsonPlace.has("Description") && jsonPlace.has("Accesibility");
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
