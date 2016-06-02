package com.jerryyu.codingassignment.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlacesApiService {
    @GET("json")
    Call<GooglePlaceData> getWithArgs(
            @Query("key") String API_KEY,
            @Query("placeid") String PLACE_ID
    );
}