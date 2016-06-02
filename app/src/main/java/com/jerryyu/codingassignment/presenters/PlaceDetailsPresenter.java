package com.jerryyu.codingassignment.presenters;

import android.os.Bundle;

public interface PlaceDetailsPresenter {
    void onDestroy();

    void onSaveInstanceState(Bundle savedInstanceState);

    void getPlaceJson(String placeId);
}