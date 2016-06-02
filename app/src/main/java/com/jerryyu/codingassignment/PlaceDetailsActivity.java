package com.jerryyu.codingassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jerryyu.codingassignment.models.GooglePlaceData;
import com.jerryyu.codingassignment.presenters.PlaceDetailsPresenterImpl;
import com.jerryyu.codingassignment.presenters.PlaceDetailsPresenter;
import com.jerryyu.codingassignment.views.PlaceDetailsView;

public class PlaceDetailsActivity extends AppCompatActivity implements PlaceDetailsView {
    public static final String KEY_PLACE_ID = "KEY_PLACE_ID";
    private PlaceDetailsPresenter placeDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_place_details);

        placeDetailsPresenter = new PlaceDetailsPresenterImpl(this);

        if (getIntent().hasExtra(KEY_PLACE_ID)) {
            placeDetailsPresenter.getPlaceJson(getIntent().getStringExtra(KEY_PLACE_ID));
        }
    }

    @Override
    public void showPlaceDetails(GooglePlaceData googlePlaceData) {
        //
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        placeDetailsPresenter.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        placeDetailsPresenter.onDestroy();

        super.onDestroy();
    }
}