package com.example.android.doordash.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Restaurants.db";
    private static final String TEXT_TYPE = " TEXT NOT NULL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            new StringBuilder().append("CREATE TABLE ").
                    append(RestaurantPersistenceContract.RestaurantEntry.TABLE_NAME)
                    .append(" (").append(RestaurantPersistenceContract.RestaurantEntry.COLUMN_NAME_RESTAURANT_ID)
                    .append(TEXT_TYPE).append(" PRIMARY KEY,")
                    .append(RestaurantPersistenceContract.RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME)
                    .append(TEXT_TYPE).append(COMMA_SEP)
                    .append(RestaurantPersistenceContract.RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS)
                    .append(TEXT_TYPE).append(COMMA_SEP)
                    .append(RestaurantPersistenceContract.RestaurantEntry.COLUMN_NAME_RESTAURANT_STATUS)
                    .append(TEXT_TYPE).append(COMMA_SEP)
                    .append(RestaurantPersistenceContract.RestaurantEntry.COLUMN_NAME_RESTAURANT_POSTER_PATH)
                    .append(TEXT_TYPE)
                    .append(");").toString();

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    public RestaurantsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
