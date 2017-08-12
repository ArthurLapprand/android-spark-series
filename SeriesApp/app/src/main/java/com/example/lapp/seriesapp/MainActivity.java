package com.example.lapp.seriesapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.lapp.seriesapp.adapters.SerieFragment;

public class MainActivity extends AppCompatActivity implements SerieFragment.OnListFragmentInteractionListener {

    SeriesApplication seriesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("TVMAZE Shows");
        setSupportActionBar(toolbar);

        SerieFragment serieFragment = new SerieFragment();

        seriesApp = (SeriesApplication) getApplicationContext();
        seriesApp.setSerieFragment(serieFragment);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.nested_scroll_view, serieFragment).commit();

        // Starts service to download and show Series/Shows
        Intent intent = new Intent(getApplicationContext(), MySeriesIntentService.class);
        startService(intent);

        /**
         * Note: since I'm loading all the series at once,
         * calling the service in onStart would be unnecessary.
          */
    }

    @Override
    public void onListFragmentInteraction(Serie item) {
        seriesApp.setItem(item);
        startActivity(new Intent(this, DetailsActivity.class));
    }
}
