package com.rohit.examples.android.bhopaldarshan.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rohit.examples.android.bhopaldarshan.Model.Place;
import com.rohit.examples.android.bhopaldarshan.R;
import com.rohit.examples.android.bhopaldarshan.Utils.Utils;

import static com.rohit.examples.android.bhopaldarshan.Activity.DetailActivity.INTENT_EXTRA;

public class PlaceDetailFragment extends Fragment implements View.OnClickListener {

    private Place place;

    /**
     * Creating a new instance of the fragment, replacing whatever current fragment instance is being shown
     *
     * @param place reference to Model class
     * @return returns Fragment with Intent received from MainActivity
     */
    public static PlaceDetailFragment newInstance(Place place) {
        PlaceDetailFragment placeDetailFragment = new PlaceDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(INTENT_EXTRA, place);
        placeDetailFragment.setArguments(bundle);
        return placeDetailFragment;
    }

    /**
     * Called to do initial creation of a fragment
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state, this is the state
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Place place = (Place) getArguments().getSerializable(INTENT_EXTRA);
            if (place != null) {
                this.place = place;
            }
        }
    }

    /**
     * Called to have the fragment instantiate its user interface view
     * Inflating the fragment layout to show Intent data received from MainActivity
     *
     * @param inflater           to inflate any views in the fragment
     * @param container          viewGroup to which the new layout is to be attached
     * @param savedInstanceState reference to savedInstanceState using which saved fragment state can be restored
     * @return returns the View for the fragment's UI, or null
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_place_fg_detail, container, false);
    }

    /**
     * Called immediately after onCreateView(LayoutInflater, ViewGroup, Bundle) has returned
     *
     * @param view               the View returned by onCreateView(LayoutInflater, ViewGroup, Bundle)
     * @param savedInstanceState reference to savedInstanceState using which saved fragment state can be restored
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Fetching view IDs for elements from resource
        ImageView placeImg = view.findViewById(R.id.placeImage);
        TextView placeTitle = view.findViewById(R.id.title);
        TextView placeRating = view.findViewById(R.id.rating);
        RatingBar placeRatingBar = view.findViewById(R.id.ratingBar);
        TextView placePhone = view.findViewById(R.id.phone);
        TextView placeHours = view.findViewById(R.id.hours);
        TextView placeDirections = view.findViewById(R.id.directions);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        //Setting up custom toolbar to show the clicked item title
        Utils.setUpToolbar(getActivity(), toolbar, place.getPlaceTitle());

        //Click listener to handle view click events
        placePhone.setOnClickListener(this);
        placeDirections.setOnClickListener(this);

        //Setting the data to appropriate item position
        placeImg.setImageResource(place.getPlaceImageId());
        placeTitle.setText(place.getPlaceTitle());
        placePhone.setText(place.getPlacePhone());
        placeHours.setText(place.getPlaceTime());
        placeRating.setText(String.valueOf(place.getPlaceRating()));
        placeRatingBar.setRating(place.getPlaceRating());
        placeDirections.setText(place.getPlaceLocation());
    }

    /**
     * Handling click events on views
     *
     * @param view view object of clicked element
     */
    @Override
    public void onClick(View view) {
        if (getContext() != null) {
            switch (view.getId()) {
                case R.id.phone:
                    Utils.phoneIntent(getContext(), place.getPlacePhone());
                    break;
                case R.id.directions:
                    Utils.directionsIntent(getContext(), place.getPlaceLocation());
                    break;
            }
        }
    }
}