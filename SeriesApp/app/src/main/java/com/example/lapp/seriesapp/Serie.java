package com.example.lapp.seriesapp;

import android.graphics.Bitmap;

/**
 * Created by Lapp on 11/08/2017.
 */

public class Serie {

    private String name;
    private String url;
    private String medImgUrl;
    private String rating;
    private String status;
    private String language;
    private String premiered;
    private Bitmap image;

    public Serie(String name, String url, String medImgUrl,
                 String rating, String status, String language,
                 String premiered) {
        this.name = name;
        this.url = url;
        this.medImgUrl = medImgUrl;
        this.rating = rating;
        this.status = status;
        this.language = language;
        this.premiered = premiered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedImgUrl() {
        return medImgUrl;
    }

    public void setMedImgUrl(String medImgUrl) {
        this.medImgUrl = medImgUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPremiered() {
        return premiered;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
