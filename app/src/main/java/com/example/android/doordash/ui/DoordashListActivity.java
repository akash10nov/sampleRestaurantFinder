package com.example.android.doordash.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.doordash.R;
import com.example.android.doordash.data.RestaurantLocalDataSource;
import com.example.android.doordash.data.RestaurantsRepository;

public class DoordashListActivity extends AppCompatActivity {

    public static final String DOORDASH_PREF = "doordash_pref";

    RestaurantsPresenter mRestaurantsPresenter;
    SharedPreferences mLastFetchTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doordash_list);
        mLastFetchTime = getSharedPreferences(DOORDASH_PREF, Context.MODE_PRIVATE);
        DoordashListFragment doordashListFragment = (DoordashListFragment) getSupportFragmentManager().findFragmentById(R.id.restaurants_fragment);
        RestaurantLocalDataSource restaurantLocalDataSource = new RestaurantLocalDataSource(getApplicationContext());
        RestaurantsRepository restaurantsRepository = new RestaurantsRepository(restaurantLocalDataSource);
        mRestaurantsPresenter = new RestaurantsPresenter(doordashListFragment, restaurantsRepository, mLastFetchTime);
    }
}

