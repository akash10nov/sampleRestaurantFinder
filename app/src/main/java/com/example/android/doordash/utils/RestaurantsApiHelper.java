package com.example.android.doordash.utils;

import android.util.Log;

import com.example.android.doordash.data.Restaurant;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class RestaurantsApiHelper {
    private static final String LATITUDE = "37.422740";
    private static final String LONGTITUDE = "-122.139956";
    private static final String OFFSET = "0";
    private static final String LIMIT = "1000";


    public static Observable<ArrayList<Restaurant>> getRestaurants() {
        String url = "https://api.doordash.com/v2/";

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        RestaurantAPICallsInterface restaurantAPICallsInterface = retrofit.create(RestaurantAPICallsInterface.class);
        final ArrayList<Restaurant>[] restaurantsList = new ArrayList[1];
        return restaurantAPICallsInterface.getRestaurantsForDoordashHQ(LATITUDE, LONGTITUDE, OFFSET, LIMIT)
                .map(list -> {
                    restaurantsList[0] = list;
                    return list;
                }).doOnError(throwable -> {
                    Log.d(RestaurantsApiHelper.class.getSimpleName(), "run:error " + throwable);
                });
    }
}
