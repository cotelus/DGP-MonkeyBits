package com.monkeybit.routability;

import android.annotation.SuppressLint;



public class ListRoute {
    //This class is the structure of the route
    private int idImage;
    private String tittle;
    private String description;

    public ListRoute(){

    }


    @SuppressLint("ValidFragment")
    public ListRoute (int idImage, String tittle, String description) {
        this.idImage = idImage;
        this.tittle = tittle;
        this.description = description;
    }

    public String get_Tittle() {
        return tittle;
    }

    public String get_Description() {
        return description;
    }

    public int get_idImagen() {
        return idImage;
    }

}
