package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements FragmentList.IListener{


    protected static final String TAG_DETAILS= "DETAILS";
    protected static final String TAG_LIST= "LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState==null){
            final FragmentList ListFragment = new FragmentList();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.data, ListFragment, TAG_LIST)
                    .commit();
        }
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