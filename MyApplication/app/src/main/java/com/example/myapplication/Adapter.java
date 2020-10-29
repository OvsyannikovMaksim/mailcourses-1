package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    protected final List<item> mData;
    protected final ViewHolder.IListener mListener;


    public Adapter(List<item> data, ViewHolder.IListener listener) {
        mListener = listener;
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View layout = inflater.inflate(R.layout.item, parent, false);

        return new ViewHolder(layout, mListener);
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position){

        final item item = mData.get(position);
        holder.bind(item);

    }

    @Override
    public int getItemCount() {

        return mData.size();

    }
}
