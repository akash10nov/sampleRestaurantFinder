package com.example.android.doordash.data;

import com.example.android.doordash.utils.RestaurantsApiHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class RestaurantsRepository {

    private RestaurantLocalDataSource mRestaurantLocalDataSource;

    public RestaurantsRepository(RestaurantLocalDataSource restaurantLocalDataSource) {
        mRestaurantLocalDataSource = restaurantLocalDataSource;
    }

    public void storeDataLocally(List<Restaurant> restaurants) {
        mRestaurantLocalDataSource.storeDataLocally(restaurants);
    }

    public rx.Observable<List<Restaurant>> fetchLocalRestaurantList() {
        return mRestaurantLocalDataSource.fetchLocalRestaurantList();
    }

    public static Observable<ArrayList<Restaurant>> getRestaurants() {
        return RestaurantsApiHelper.getRestaurants();
    }
}
