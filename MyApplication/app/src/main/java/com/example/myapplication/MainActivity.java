package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentList.IListener{


    protected static final String TAG_DETAILS= "DETAILS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    protected void showDetails(Item item) {

        final FragmentDetails detailsFragment = FragmentDetails.newInstance(item);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.data, detailsFragment, TAG_DETAILS)
                .addToBackStack(null)
                .commit();

    }


    @Override
    public void onClicked(Item item) {
        showDetails(item);
    }
}