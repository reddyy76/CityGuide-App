package com.rohit.examples.android.bhopaldarshan.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohit.examples.android.bhopaldarshan.Adapter.PlaceAdapter;
import com.rohit.examples.android.bhopaldarshan.Adapter.RestaurantAdapter;
import com.rohit.examples.android.bhopaldarshan.Data.PlaceData;
import com.rohit.examples.android.bhopaldarshan.Data.RestaurantData;
import com.rohit.examples.android.bhopaldarshan.R;

public class RestaurantFragment extends Fragment {
    public RestaurantFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(10);

        if (getContext() != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setAdapter(new RestaurantAdapter(getContext(), RestaurantData.fetchRestaurantData(getContext())));
        }
    }
}