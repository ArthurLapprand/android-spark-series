package com.example.lapp.seriesapp.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lapp.seriesapp.R;
import com.example.lapp.seriesapp.Serie;
import com.example.lapp.seriesapp.adapters.SerieFragment.OnListFragmentInteractionListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MySerieRecyclerViewAdapter extends RecyclerView.Adapter<MySerieRecyclerViewAdapter.ViewHolder> {

    private final List<Serie> mSeries;
    private final OnListFragmentInteractionListener mListener;

    public MySerieRecyclerViewAdapter(List<Serie> items, OnListFragmentInteractionListener listener) {
        mSeries = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.serie_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Serie mSerie = mSeries.get(position);
        holder.mSerie = mSerie;
        holder.mNameView.setText(mSerie.getName());
        holder.mStatusView.setText(mSerie.getStatus());
        holder.mPremieredView.setText(mSerie.getPremiered());

        if (holder.mImageView.getDrawable() != null) holder.mImageView.setVisibility(View.GONE);

        new ImageTask(holder.mImageView).execute(holder.mSerie.getMedImgUrl());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    holder.mSerie.setImage(((BitmapDrawable) holder.mImageView.getDrawable()).getBitmap());
                    mListener.onListFragmentInteraction(holder.mSerie);
                }
            }
        });
    }

    private class ImageTask extends AsyncTask<Object, Void, Bitmap> {

        URL url;
        Bitmap image;
        ImageView imageView;

        ImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Object[] objects) {
            try {
                url = new URL((String) objects[0]);
                image = BitmapFactory.decodeStream(url.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap v) {
            if (url != null) {
                this.imageView.setImageBitmap(v);
                this.imageView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mSeries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mStatusView;
        public final TextView mPremieredView;
        public final ImageView mImageView;
        public Serie mSerie;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.serie_name_view);
            mStatusView = (TextView) view.findViewById(R.id.serie_status_view);
            mPremieredView = (TextView) view.findViewById(R.id.serie_premiered_view);
            mImageView = (ImageView) view.findViewById(R.id.serie_image_view);
        }
    }
}
