package com.monkeybit.routability;

import android.annotation.SuppressLint;

public class ListPlace {
    private String idImage;
    private String tittle;
    private String description;
    private String rating;
    private String idPlace;

    @SuppressLint("ValidFragment")
    public ListPlace(String idPlace, String idImage, String tittle, String description/*, String rating*/){
        this.idPlace = idPlace;
        this.idImage = idImage;
        this.tittle = tittle;
        this.description = description;
        /*this.rating = rating;*/
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
    }
}
