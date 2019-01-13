package com.example.android.doordash.data;

import android.provider.BaseColumns;

public final class RestaurantPersistenceContract {
    public static abstract class RestaurantEntry implements BaseColumns {
        public static final String TABLE_NAME = "restaurants";
        public static final String COLUMN_NAME_RESTAURANT_ID = "restaurantId";
        public static final String COLUMN_NAME_RESTAURANT_NAME = "restaurantTitle";
        public static final String COLUMN_NAME_RESTAURANT_TAGS = "restaurantTags";
        public static final String COLUMN_NAME_RESTAURANT_POSTER_PATH = "posterPath";
        public static final String COLUMN_NAME_RESTAURANT_STATUS = "distance";
        /*
         mId = id;
        mTitle = title;
        mTags = tags;
        mAvgRating = avg_rating;
        mStatus = asapTime;
        mPosterPath = coverImage;
        mStatusType = statusType;
         */
    }
}
