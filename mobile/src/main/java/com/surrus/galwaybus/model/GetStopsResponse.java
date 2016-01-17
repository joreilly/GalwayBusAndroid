package com.surrus.galwaybus.model;

import java.util.List;

public class GetStopsResponse {

    BusRoute route;
    List<List<BusStop>> stops;

    public BusRoute getRoute() {
        return route;
    }

    public List<List<BusStop>> getStops() {
        return stops;
    }
}
