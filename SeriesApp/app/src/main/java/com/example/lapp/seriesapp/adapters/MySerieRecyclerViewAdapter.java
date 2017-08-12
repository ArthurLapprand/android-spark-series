package com.example.lapp.seriesapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lapp.seriesapp.R;
import com.example.lapp.seriesapp.Serie;
import com.example.lapp.seriesapp.adapters.SerieFragment.OnListFragmentInteractionListener;

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
        holder.mSerie = mSeries.get(position);
        holder.mNameView.setText(mSeries.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mSerie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSeries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public Serie mSerie;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.serie_name_view);
        }
    }
}
