package com.example.android.doordash.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.doordash.data.RestaurantPersistenceContract.RestaurantEntry.COLUMN_NAME_RESTAURANT_ID;
import static com.example.android.doordash.data.RestaurantPersistenceContract.RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME;
import static com.example.android.doordash.data.RestaurantPersistenceContract.RestaurantEntry.COLUMN_NAME_RESTAURANT_POSTER_PATH;
import static com.example.android.doordash.data.RestaurantPersistenceContract.RestaurantEntry.COLUMN_NAME_RESTAURANT_STATUS;
import static com.example.android.doordash.data.RestaurantPersistenceContract.RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS;
import static com.example.android.doordash.data.RestaurantPersistenceContract.RestaurantEntry.TABLE_NAME;

public class RestaurantLocalDataSource {

    private RestaurantsDbHelper mRestaurantsDbHelper;

    public RestaurantLocalDataSource(Context context) {
        mRestaurantsDbHelper = new RestaurantsDbHelper(context);
    }

    public void storeDataLocally(List<Restaurant> restaurants) {
        SQLiteDatabase sqLiteDatabase = mRestaurantsDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < restaurants.size(); i++) {
            contentValues.put(COLUMN_NAME_RESTAURANT_ID, restaurants.get(i).getId());
            contentValues.put(COLUMN_NAME_RESTAURANT_NAME, restaurants.get(i).getTitle());
            contentValues.put(COLUMN_NAME_RESTAURANT_STATUS, restaurants.get(i).getStatus());
            contentValues.put(COLUMN_NAME_RESTAURANT_TAGS, restaurants.get(i).getTags());
            contentValues.put(COLUMN_NAME_RESTAURANT_POSTER_PATH, restaurants.get(i).getPosterPath());
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " +
                    TABLE_NAME + " WHERE restaurantId =" + restaurants.get(i).getId(), null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
            }

            long result = sqLiteDatabase.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public rx.Observable<List<Restaurant>> fetchLocalRestaurantList() {
        SQLiteDatabase sqLiteDatabase = mRestaurantsDbHelper.getReadableDatabase();
        // TODO : check if projection null works
        List<Restaurant> restaurants = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_RESTAURANT_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_RESTAURANT_NAME));
                String posterPath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_RESTAURANT_POSTER_PATH));
                String tags = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_RESTAURANT_TAGS));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_RESTAURANT_STATUS));
                restaurants.add(new Restaurant(id, title, status, tags, posterPath));
            }
        }
        if (cursor != null) {
            cursor.close();
        }

        return rx.Observable.just(restaurants);
    }
}
