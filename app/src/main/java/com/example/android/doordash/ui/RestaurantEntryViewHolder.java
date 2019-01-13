package com.example.android.doordash.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.doordash.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantEntryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.restaurant_container)
    LinearLayout mContainer;

    @BindView(R.id.restaurant_name)
    TextView mName;

    @BindView(R.id.restaurant_tags)
    TextView mTags;

    @BindView(R.id.restautant_status)
    TextView mStatus;

    @BindView(R.id.restaurant_image)
    ImageView mImage;


    public RestaurantEntryViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
