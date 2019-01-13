package com.example.android.doordash.ui;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.doordash.data.RestaurantLocalDataSource;
import com.example.android.doordash.utils.RestaurantsApiHelper;

public class RestaurantsPresenter implements RestaurantContract.Presenter {
    private final static long MILLI_SEC_IN_1_MIN = 60000l;
    private final static String LAST_FETCH_TIME = "last_fetch_time";
    private final RestaurantContract.View mRestaurantView;
    private RestaurantLocalDataSource mRestaurantLocalDataSource;
    private SharedPreferences mLastFetchTime;


    public RestaurantsPresenter(@NonNull RestaurantContract.View restaurantsView,
                                RestaurantLocalDataSource restaurantLocalDataSource,
                                SharedPreferences lastFetchTime) {
        mRestaurantView = restaurantsView;
        mRestaurantLocalDataSource = restaurantLocalDataSource;
        mLastFetchTime = lastFetchTime;
        mRestaurantView.setPresenter(this);
    }

    @Override
    public void loadRestaurants(boolean forceUpdate) {
        long time = mLastFetchTime.getLong(LAST_FETCH_TIME, 0);


        Thread loadRestaurantThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // We can control this usually through experiment manager, we can fetch data every 1 minute or every 1 hour
                    if ((System.currentTimeMillis() - time) > MILLI_SEC_IN_1_MIN) {
                        RestaurantsApiHelper.getRestaurants()
                                .doOnNext(restaurants -> {
                                            // Would add something around here to see if I really need to fetch data, if not then only this thread will run
                                            mRestaurantLocalDataSource.storeDataLocally(restaurants);
                                            mLastFetchTime.edit().putLong(LAST_FETCH_TIME, System.currentTimeMillis()).apply();
                                            mRestaurantView.showRestaurants(restaurants);
                                        }
                                ).doOnError(throwable -> {
                            Log.d(RestaurantsPresenter.class.getSimpleName(), "run: " + throwable);
                        })
                                .subscribe();
                    } else {
                        mRestaurantLocalDataSource.fetchLocalRestaurantList()
                                .doOnNext(restaurants -> {
                                            mRestaurantView.showRestaurants(restaurants);
                                        }
                                ).doOnError(throwable -> {
                            Log.d(RestaurantsPresenter.class.getSimpleName(), "run: " + throwable);
                        })
                                .subscribe();
                    }
                } catch (final Exception e) {
                    Log.d(RestaurantsPresenter.class.getSimpleName(), "throwable: " + e);
                }
            }
        });
        loadRestaurantThread.start();
    }

    @Override
    public void start() {
        // here we can decide if we want to store data locally or everytime fetch from the server, the reason
        // why I am fetching each time is because I dont know how users use the app and what are other variables
        // for example if I had restautants open hours in time format I would store that and run that againt local time
        // to show if restaurant is open (update the database every few hours) but I dont see anything like that so I have
        // to make a request each time
        loadRestaurants(true);
    }
}
