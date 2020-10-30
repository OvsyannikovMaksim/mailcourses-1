package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.Serializable;

public class FragmentDetails extends Fragment {

    protected static final String KEY = "KEY";

    public static FragmentDetails newInstance(Item item) {

        final Bundle extras = new Bundle();
        extras.putSerializable(KEY, item);

        final FragmentDetails fragment = new FragmentDetails();
        fragment.setArguments(extras);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Item item = info();
        if (item != null) {

            ((TextView) view.findViewById(R.id.name)).setText(item.name);

            final TextView mName = view.findViewById(R.id.name);

            switch (item.colour) {
                case 0:
                    mName.setTextColor(ContextCompat.getColor(view.getContext(), R.color.red));
                    break;

                case 1:
                    mName.setTextColor(ContextCompat.getColor(view.getContext(), R.color.blue));
                    break;

            }
        }
    }
    public Item info(){
        if (getArguments() == null) {
            return null;
        }

        final Serializable droid = getArguments().getSerializable(KEY);

        return (Item) droid;

    }
}
