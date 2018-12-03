package com.monkeybit.routability;

import org.json.JSONObject;

public class Route {

    private String idRoute, email, madeBy, name, description, accesibility;

    public Route() {}

    public Route(JSONObject jsonRoute) {
        assert (!isValidJson(jsonRoute));
        this.setIdRoute(jsonRoute.optString("IdRoute"));
        this.setEmail(jsonRoute.optString("Email"));
        this.setMadeBy(jsonRoute.optString("MadeBy"));
        this.setName(jsonRoute.optString("Name"));
        this.setDescription(jsonRoute.optString("Description"));
        this.setAccesibility(jsonRoute.optString("Accesiblity"));
    }

    private boolean isValidJson(JSONObject jsonRoute) {
        return jsonRoute.has("IdRoute") && jsonRoute.has("Email") && jsonRoute.has("MadeBy")
                && jsonRoute.has("Name") && jsonRoute.has("Description") && jsonRoute.has("Accesibility");
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

    public String getAccesibility() {
        return accesibility;
    }

    public void setAccesibility(String accesibility) {
        this.accesibility = accesibility;
    }
}
