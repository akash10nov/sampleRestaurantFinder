package com.example.android.doordash.utils;

import com.example.android.doordash.data.Restaurant;

import java.util.ArrayList;

import retrofit2.http.GET;
import rx.Observable;

public interface RestaurantAPICallsInterface {

    @GET("restaurant/?lat=37.422740&lng=-122.139956&offset=0&limit=1000")
    Observable<ArrayList<Restaurant>> getRestaurantsForDoordashHQ();
}
