package com.example.lapp.seriesapp;

import android.app.Application;

import com.example.lapp.seriesapp.adapters.SerieFragment;

import java.util.ArrayList;

/**
 * Created by Lapp on 11/08/2017.
 */

public class SeriesApplication extends Application {

    ArrayList<Serie> series;
    SerieFragment serieFragment;
    Serie item;

    @Override
    public void onCreate() {
        super.onCreate();
        this.series = new ArrayList<>();
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
        this.serieFragment.notifyAdapter();
    }

    public SerieFragment getSerieFragment() {
        return serieFragment;
    }

    public void setSerieFragment(SerieFragment serieFragment) {
        this.serieFragment = serieFragment;
    }

    public Serie getItem() {
        return item;
    }

    public void setItem(Serie item) {
        this.item = item;
    }
}
