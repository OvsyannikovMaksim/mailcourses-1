package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    public interface IListener {
        void onClicked(int position);
    }

    protected final TextView mName;
    protected final IListener mListener;


    public ViewHolder(@NonNull View itemView, IListener listener) {
        super(itemView);

        mListener = listener;
        mName = itemView.findViewById(R.id.name);

        final View.OnClickListener clickListener = v -> mListener.onClicked(getAdapterPosition());

        itemView.setOnClickListener(clickListener);
    }

    void bind(Item item) {

        mName.setText(item.name);

        switch (item.colour) {
            case 1:
                mName.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.blue));
                break;

            case 0:
                mName.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.red));
                break;
        }
    }
}
