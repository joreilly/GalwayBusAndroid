package com.surrus.galwaybus.service;

import android.util.Log;

import com.surrus.galwaybus.events.DeparturesLoadedEvent;
import com.surrus.galwaybus.events.StopsLoadedEvent;
import com.surrus.galwaybus.model.BusRoute;
import com.surrus.galwaybus.model.GetDeparturesResponse;
import com.surrus.galwaybus.model.GetStopsResponse;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

@Singleton
public class GalwayBusService {

    private GalwayBusRestInterface galwayBusResetInterface;

    @Inject
    EventBus bus;

    @Inject
    public GalwayBusService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://galwaybus.herokuapp.com/")
                .build();
        galwayBusResetInterface = restAdapter.create(GalwayBusRestInterface.class);
    }


    public Observable<Map<String, BusRoute>> getRoutes() {
        return galwayBusResetInterface.getRoutes();
    }

    public void getStops(int routeId) {

        galwayBusResetInterface.getStops(routeId,
                new Callback<GetStopsResponse>() {
                    @Override
                    public void success(GetStopsResponse getStopsResponse, Response response) {
                        Log.d("GalwayBusService", "got stops");
                        bus.post(new StopsLoadedEvent(getStopsResponse.getStops()));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("GalwayBusService", error.getMessage());
                    }
                }
        );
    }



    public void getDepartures(String stopRef) {

        galwayBusResetInterface.getDepartures(stopRef,
                new Callback<GetDeparturesResponse>() {
                    @Override
                    public void success(GetDeparturesResponse getDeparturesResponse, Response response) {
                        Log.d("GalwayBusService", "got departures");
                        bus.post(new DeparturesLoadedEvent(getDeparturesResponse.getDepartureTimes()));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("GalwayBusService", error.getMessage());
                    }
                }
        );
    }
}
