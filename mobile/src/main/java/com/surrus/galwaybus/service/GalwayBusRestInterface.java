package com.surrus.galwaybus.service;

import com.surrus.galwaybus.model.BusRoute;
import com.surrus.galwaybus.model.GetDeparturesResponse;
import com.surrus.galwaybus.model.GetStopsResponse;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GalwayBusRestInterface {

    @GET("/routes.json")
    Map<String,BusRoute> getRoutes();

    @GET("/routes/{route_id}.json")
    GetStopsResponse getStops(@Path("route_id") int routeId);

    @GET("/stops/{stop_ref}.json")
    GetDeparturesResponse getDepartures(@Path("stop_ref") String stopRef);


    @GET("/routes.json")
    void getRoutes(Callback<Map<String, BusRoute>> cb);

    @GET("/routes/{route_id}.json")
    void getStops(@Path("route_id") int routeId, Callback<GetStopsResponse> cb);

    @GET("/stops/{stop_ref}.json")
    void getDepartures(@Path("stop_ref") String stopRef, Callback<GetDeparturesResponse> cb);

}
