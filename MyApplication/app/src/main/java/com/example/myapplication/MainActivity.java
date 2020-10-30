package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentList.IListener{


    protected static final String TAG_DETAILS_DIALOG = "TAG_DETAILS_DIALOG";
    protected static final String FL_TAG = "FL_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    protected void showDetails(Item item) {

        final FragmentDetails detailsFragment = FragmentDetails.newInstance(item);
        detailsFragment.show(getSupportFragmentManager(), TAG_DETAILS_DIALOG);

    }


    @Override
    public void onClicked(Item item) {
        showDetails(item);
    }
}