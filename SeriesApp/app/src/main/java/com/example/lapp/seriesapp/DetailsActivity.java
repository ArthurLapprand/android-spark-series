package com.example.lapp.seriesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private Serie serie;
    private TextView nameView;
    private TextView statusView;
    private TextView ratingView;
    private TextView languageView;
    private TextView urlView;
    private TextView premieredView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        serie = ((SeriesApplication) getApplicationContext()).getItem();

        imageView = (ImageView) findViewById(R.id.d_image_view);
        nameView = (TextView) findViewById(R.id.d_name_view);
        statusView = (TextView) findViewById(R.id.d_status_view);
        ratingView = (TextView) findViewById(R.id.d_rating_view);
        languageView = (TextView) findViewById(R.id.d_language_view);
        urlView = (TextView) findViewById(R.id.d_url_view);
        premieredView = (TextView) findViewById(R.id.d_premiered_view);

        imageView.setImageBitmap(serie.getImage());
        nameView.setText(serie.getName());
        statusView.setText(serie.getStatus());
        ratingView.setText(serie.getRating());
        languageView.setText(serie.getLanguage());
        urlView.setText(serie.getUrl());
        premieredView.setText(serie.getPremiered());
    }
}
