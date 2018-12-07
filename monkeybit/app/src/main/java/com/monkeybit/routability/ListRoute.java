package com.monkeybit.routability;

import android.annotation.SuppressLint;



public class ListRoute {
    //This class is the structure of the route
    private int idImage;
    private String tittle;
    private String description;
    private double rating;
    private double people;
    String id;

    public ListRoute(){

    }


    @SuppressLint("ValidFragment")
    public ListRoute (int idImage, String tittle, String description,String id) {
        this.idImage = idImage;
        this.tittle = tittle;
        this.description = description;
        rating = 0;
        people = 0;
        this.id = id;
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

    //people
    public void SetRating(double rat){
        rating = rating + rat;
        people += 1;
    }

    public double get_Rating(){
        return rating/people;
    }

}
