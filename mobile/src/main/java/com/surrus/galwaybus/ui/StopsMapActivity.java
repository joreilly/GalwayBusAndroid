package com.surrus.galwaybus.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.surrus.galwaybus.R;

public class StopsMapActivity extends AppCompatActivity {

    public static final String STOP_LAT_ARG="STOP_LAT_ARG";
    public static final String STOP_LONG_ARG="STOP_LONG_ARG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stops_map);


        Bundle b = getIntent().getExtras();
        double latitude = b.getDouble(STOP_LAT_ARG);
        double longitude = b.getDouble(STOP_LONG_ARG);


        if (savedInstanceState == null) {

            Fragment fragment = new StopsMapFragment();
            Bundle args = new Bundle();
            args.putDouble(STOP_LAT_ARG, latitude);
            args.putDouble(STOP_LONG_ARG, longitude);
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class StopsMapFragment extends Fragment {

        public StopsMapFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_stops_map, container, false);
            return rootView;
        }


        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            double latitude = getArguments().getDouble(STOP_LAT_ARG);
            double longitude = getArguments().getDouble(STOP_LONG_ARG);
            final LatLng coordinate = new LatLng(latitude, longitude);

            ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(coordinate)
                            .title("Stop"));

                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 18.0f));
                }
            });

        }

    }
}
