package com.example.android.doordash.ui;

import com.example.android.doordash.BasePresenter;
import com.example.android.doordash.BaseView;
import com.example.android.doordash.data.Restaurant;

import java.util.List;

public class RestaurantContract {

    interface View extends BaseView<Presenter> {
        void showRestaurants(List<Restaurant> restaurants);
    }

    interface Presenter extends BasePresenter {

        void loadRestaurants(boolean forceUpdate);
    }
}
