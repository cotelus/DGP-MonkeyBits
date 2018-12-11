package com.monkeybit.routability;

import android.media.Image;

public class Comments {
    private String image;
    private String author;
    private String description;

    public Comments(String image, String author, String description){
        this.image = image;
        this.author = author;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
