package com.jerryyu.codingassignment.presenters;

import android.os.Bundle;

import com.jerryyu.codingassignment.models.GooglePlaceData;
import com.jerryyu.codingassignment.models.GooglePlacesApiService;
import com.jerryyu.codingassignment.views.PlaceDetailsView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaceDetailsPresenterImpl implements PlaceDetailsPresenter {
    private PlaceDetailsView placeDetailsView;
    private GooglePlaceData googlePlaceData;

    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/details/";

    public PlaceDetailsPresenterImpl(PlaceDetailsView placeDetailsView) {
        this.placeDetailsView = placeDetailsView;
    }

    @Override
    public void getPlaceJson(String placeId) {
        /*
         * Mostly adapted from https://www.metachris.com/2015/10/retrofit-2-samples
         */

        // Retrofit setup
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Service setup
        GooglePlacesApiService googlePlacesApiService = retrofit.create(GooglePlacesApiService.class);

        // Prepare the HTTP request
        Call<GooglePlaceData> call = googlePlacesApiService.getWithArgs("AIzaSyBcO-OHWImouOHPHOe3T8Y37zUk2ZKUq1c", placeId);

        // Asynchronously execute HTTP request
        call.enqueue(new Callback<GooglePlaceData>() {
            /**
             * onResponse is called when any kind of response has been received.
             */
            public void onResponse(Response<GooglePlaceData> response, Retrofit retrofit) {
                // http response status code + headers
                System.out.println("Response status code: " + response.code());

                // isSuccess is true if response code => 200 and <= 300
                if (!(response.code() >= 200 && response.code() <= 300)) {
                    // print response body if unsuccessful
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        // do nothing
                    }
                    return;
                }

                // if parsing the JSON body failed, `response.body()` returns null
                GooglePlaceData decodedResponse = response.body();

                if (decodedResponse == null) {
                    return;
                }

                googlePlaceData = decodedResponse;

                placeDetailsView.showPlaceDetails(googlePlaceData);
            }

            public void onResponse(Call<GooglePlaceData> call, Response<GooglePlaceData> response) {
                // http response status code + headers
                System.out.println("Response status code: " + response.code());

                // isSuccess is true if response code => 200 and <= 300
                if (!(response.code() >= 200 && response.code() <= 300)) {
                    // print response body if unsuccessful
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        // do nothing
                    }
                    return;
                }

                // if parsing the JSON body failed, `response.body()` returns null
                GooglePlaceData decodedResponse = response.body();

                if (decodedResponse == null) {
                    return;
                }

                googlePlaceData = decodedResponse;

                placeDetailsView.showPlaceDetails(googlePlaceData);
            }

            /**
             * onFailure gets called when the HTTP request didn't get through.
             * For instance if the URL is invalid / host not reachable
             */
            public void onFailure(Call<GooglePlaceData> call, Response<GooglePlaceData> response) {
                System.out.println("onFailure");
                System.out.println(response.errorBody());
            }

            public void onFailure(Call<GooglePlaceData> call, Throwable t) {
                System.out.println("onFailure");
                System.out.println(t.getMessage());
            }
        });
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