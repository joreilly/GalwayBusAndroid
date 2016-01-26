package com.surrus.galwaybus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surrus.galwaybus.R;
import com.surrus.galwaybus.events.RoutesLoadedEvent;
import com.surrus.galwaybus.model.BusRoute;
import com.surrus.galwaybus.service.GalwayBusService;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RoutesFragment extends BaseFragment {

    @Inject
    GalwayBusService galwayBusService;


    @Bind(R.id.list)
    RecyclerView routesListView;

    private BusRoutesRecyclerViewAdapter busRoutesAdapter;

    public RoutesFragment() {
    }


    @SuppressWarnings("unused")
    public static RoutesFragment newInstance() {
        RoutesFragment fragment = new RoutesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_busroute_list, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        busRoutesAdapter = new BusRoutesRecyclerViewAdapter();
        routesListView.setAdapter(busRoutesAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        galwayBusService.getRoutes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(busRoutes -> {
                    busRoutesAdapter.setBusRoutes(new ArrayList<>(busRoutes.values()));
                    busRoutesAdapter.notifyDataSetChanged();
                });
    }


    @SuppressWarnings("UnusedDeclaration") // Called by the event bus.
    public void onEventMainThread(RoutesLoadedEvent event) {

        busRoutesAdapter.setBusRoutes(event.getBusRoutes());
        busRoutesAdapter.notifyDataSetChanged();
    }



    class BusRoutesRecyclerViewAdapter extends RecyclerView.Adapter<BusRoutesRecyclerViewAdapter.ViewHolder> {

        private List<BusRoute> busRouteList;

        public void setBusRoutes(ArrayList<BusRoute> busRouteList) {
            this.busRouteList = busRouteList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_busroutes_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.bind(busRouteList.get(position));
        }

        @Override
        public int getItemCount() {
            return busRouteList != null ? busRouteList.size() : 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @Bind(R.id.title) TextView titleTextView;
            @Bind(R.id.subtitle) TextView subTitleTextView;
            private BusRoute busRoute;

            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                ButterKnife.bind(this, itemView);
            }

            public void bind(BusRoute busRoute) {
                this.busRoute = busRoute;
                titleTextView.setText(busRoute.getLongName());
                subTitleTextView.setText(Integer.toString(busRoute.getTimetableId()));
            }

            @Override
            public void onClick(View v) {
                Intent i = new Intent(RoutesFragment.this.getActivity(), StopsActivity.class);
                i.putExtra(StopsActivity.ROUTE_ID_ARG, busRoute.getTimetableId());
                startActivity(i);
            }

        }
    }

}


