package com.example.lapp.seriesapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lapp on 11/08/2017.
 */

public class DownloaderTask extends AsyncTask<Object, Void, String> {

    @Override
    protected String doInBackground(Object[] objects) {
        String result = "";
        try {
            result = getSeries((String) objects[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("RESULTS", result);
        return result;
    }

    private String getSeries(String seriesUrl) throws IOException {
        InputStream in = null;
        String series = "";
        try {
            URL url = new URL(seriesUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            in = conn.getInputStream();
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
