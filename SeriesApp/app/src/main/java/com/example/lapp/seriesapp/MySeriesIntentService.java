package com.example.lapp.seriesapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MySeriesIntentService extends IntentService {

    public MySeriesIntentService() {
        super("MySeriesIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Gets saved url
        String base_url = getResources().getString(R.string.base_url);

        String seriesStr = "";
        try {
            seriesStr = getSeries(base_url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Parsing JSON String to Objects
        try {
            JSONArray jsonArray = new JSONArray(seriesStr);

            ArrayList<Serie> series = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = (JSONObject) jsonArray.get(i);
                series.add(new Serie(
                        o.get("name").toString(),
                        o.get("url").toString(),
                        ((JSONObject) o.get("image")).get("medium").toString(),
                        ((JSONObject) o.get("rating")).get("average").toString(),
                        o.get("status").toString(),
                        o.get("language").toString()
                ));
            }

            SeriesApplication seriesApp = (SeriesApplication) getApplicationContext();
            seriesApp.setSeries(series);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method which fetches series data
     */
    private String getSeries(String seriesUrl) throws IOException {
        InputStream in = null;
        String series = "";
        try {
            URL url = new URL(seriesUrl);
            in = url.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int count; (count = in.read(buffer)) != -1; ) {
                out.write(buffer, 0, count);
            }
            byte[] response = out.toByteArray();
            series = new String(response, "UTF-8");
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return series;
    }
}
