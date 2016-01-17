package com.surrus.galwaybus.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.surrus.galwaybus.R;
import com.surrus.galwaybus.events.StopsLoadedEvent;
import com.surrus.galwaybus.model.BusStop;
import com.surrus.galwaybus.service.GalwayBusService;

import java.util.List;

import javax.inject.Inject;


public class StopsActivity extends BaseActivity {
    public static final String ROUTE_ID_ARG = "routeId";

    @Inject
    GalwayBusService galwayBusService;

    SectionsPagerAdapter pagerAdapter;

    ViewPager viewPager;

    private int routeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stops);

        if (savedInstanceState != null) {
            routeId= savedInstanceState.getInt(ROUTE_ID_ARG);
        } else {
            Bundle b = getIntent().getExtras();
            routeId = b.getInt(ROUTE_ID_ARG);
        }
        setTitle(Integer.toString(routeId));

        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.stops_direction1)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.stops_direction2)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        galwayBusService.getStops(routeId);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ROUTE_ID_ARG, routeId);
        super.onSaveInstanceState(outState);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("UnusedDeclaration") // Called by the event bus.
    public void onEventMainThread(StopsLoadedEvent event) {
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return StopsFragment.newInstance(routeId, position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


    public static class StopsFragment extends BaseFragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        private ListView listView;
        private BusStopsAdapter busStopsAdapter;

        public static StopsFragment newInstance(int routeId, int sectionNumber) {
            StopsFragment fragment = new StopsFragment();
            Bundle args = new Bundle();
            args.putInt(ROUTE_ID_ARG, routeId);
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public StopsFragment() {
        }


        @SuppressWarnings("UnusedDeclaration") // Called by the event bus.
        public void onEventMainThread(StopsLoadedEvent event) {
            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            busStopsAdapter.setBusStops(event.getBusStops().get(sectionNumber - 1));
            busStopsAdapter.notifyDataSetChanged();
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_stops, container, false);
            listView = (ListView)rootView.findViewById(R.id.listView);
            busStopsAdapter = new BusStopsAdapter(this.getActivity());
            listView.setAdapter(busStopsAdapter);
            return rootView;
        }


        @Override
        public void onResume() {
            super.onResume();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BusStop busStop = (BusStop)listView.getItemAtPosition(position);

                    Intent i = new Intent(StopsFragment.this.getActivity(), StopsMapActivity.class);
                    i.putExtra(StopsMapActivity.STOP_LAT_ARG, busStop.getLatitude());
                    i.putExtra(StopsMapActivity.STOP_LONG_ARG, busStop.getLongitude());
                    startActivity(i);
                }
            });
        }

    }



}


class BusStopsAdapter extends BaseAdapter {
    private final Context context;
    private List<BusStop> busStopsList;

    public BusStopsAdapter(Context context) {
        super();
        this.context = context;
    }

    public void setBusStops(List<BusStop> busStopsList) {
        this.busStopsList = busStopsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.stop_list_item, parent, false);

        BusStop busStop = busStopsList.get(position);

        TextView firstLineTextView = (TextView) rowView.findViewById(R.id.firstLine);
        firstLineTextView.setText(busStop.getLongName());

        TextView secondLineTextView = (TextView) rowView.findViewById(R.id.secondLine);
        secondLineTextView.setText(busStop.getIrishShortName());

        return rowView;
    }

    @Override
    public int getCount() {
        return busStopsList != null ? busStopsList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return busStopsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return busStopsList.get(i).getStopId();
    }
}