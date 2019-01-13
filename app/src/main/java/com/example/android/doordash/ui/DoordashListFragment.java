package com.example.android.doordash.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.doordash.R;
import com.example.android.doordash.data.Restaurant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DoordashListFragment extends Fragment implements RestaurantContract.View {

    private RestaurantContract.Presenter mPresenter;
    RestaurantAdapter mRestaurantAdapter;

    private Unbinder mUnbinder;

    @BindView(R.id.restaurant_recycleview)
    RecyclerView mRestaurantRecyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRestaurantAdapter = new RestaurantAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup containter, @Nullable Bundle savedBundleInstance) {
        View view = inflater.inflate(R.layout.fragment_restaurants, containter, false);
        mUnbinder = ButterKnife.bind(this, view);
        mRestaurantRecyclerView.setAdapter(mRestaurantAdapter);
        mRestaurantRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRestaurantRecyclerView.setItemViewCacheSize(20);
        mRestaurantRecyclerView.setDrawingCacheEnabled(true);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(RestaurantContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @UiThread
    @Override
    public void showRestaurants(final List<Restaurant> restaurants) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("replace", "run: Replace Data");
                mRestaurantAdapter.replaceData(restaurants);
            }
        });
    }
}
