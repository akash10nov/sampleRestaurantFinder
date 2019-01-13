package com.example.android.doordash.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.doordash.R;
import com.example.android.doordash.data.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Restaurant> mRestaurants;

    Context mContext;

    public void replaceData(List<Restaurant> restaurants) {
        mRestaurants = restaurants;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int layoutIdForListItem = R.layout.restaurant_entry_viewholder;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean shouldAttachtoParentImmidiately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachtoParentImmidiately);
        RecyclerView.ViewHolder viewHolder = new RestaurantEntryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Restaurant restaurant = mRestaurants.get(position);
        final RestaurantEntryViewHolder restaurantEntryViewHolder = (RestaurantEntryViewHolder) holder;

        restaurantEntryViewHolder.mName.setText(restaurant.getTitle());
        restaurantEntryViewHolder.mTags.setText(restaurant.getTags());
        restaurantEntryViewHolder.mStatus.setText(restaurant.getStatus());

        String posterUri = "";
        posterUri = restaurant.getPosterPath();

        Picasso.with(mContext).load(posterUri).into(restaurantEntryViewHolder.mImage);
    }

    @Override
    public int getItemCount() {
        if (mRestaurants != null) {
            return mRestaurants.size();
        }
        return 0;
    }
}
