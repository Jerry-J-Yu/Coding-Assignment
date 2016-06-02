package com.jerryyu.codingassignment.presenters;

import android.os.Bundle;

import com.jerryyu.codingassignment.models.GooglePlaceData;
import com.jerryyu.codingassignment.views.PlaceDetailsView;

public class PlaceDetailsPresenterImpl implements PlaceDetailsPresenter {
    private PlaceDetailsView placeDetailsView;
    private GooglePlaceData googlePlaceData;

    public PlaceDetailsPresenterImpl(PlaceDetailsView placeDetailsView) {
        this.placeDetailsView = placeDetailsView;
    }

    @Override
    public void getPlaceJson(String placeId) {
        //
    }

    @Override
    public void onDestroy() {
        this.placeDetailsView = null;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // TODO: make googlePlaceData implement Parcelable and store it in the savedInstanceState
    }
}