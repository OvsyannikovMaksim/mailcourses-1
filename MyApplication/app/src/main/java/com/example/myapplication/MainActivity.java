package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentList.IListener{

    private static final int DEFAULT_INDEX = 0;
    private static final String KEY = "KEY";
    protected static final String TAG_DETAILS = "DETAILS";
    protected static final String TAG_DETAILS_DIALOG = "TAG_DETAILS_DIALOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final boolean isDual = getResources().getBoolean(R.bool.is_dual);

        if (savedInstanceState == null) {
            Log.d("123123!!!","If savedInstanceState fragment == null");
            if (isDual) {
                final Item droid = ItemRep.getInstance().item(DEFAULT_INDEX);
                showDetails(droid);
            }
        }
        else{
            Log.d("123123!!!","If savedInstanceState fragment != null");
        }
    }

    protected void showDetails(Item item) {
        if (item == null) {

            return;
        }


        final FragmentDetails detailsFragment = FragmentDetails.newInstance(item);

        final boolean isDual = getResources().getBoolean(R.bool.is_dual);
        if (isDual) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.details, detailsFragment, TAG_DETAILS)        // заменить фрагмент
                    .commitAllowingStateLoss();
        } else {

            detailsFragment.show(getSupportFragmentManager(), TAG_DETAILS_DIALOG);
        }
    }


    @Override
    public void onClicked(Item item) {
        showDetails(item);
    }
}