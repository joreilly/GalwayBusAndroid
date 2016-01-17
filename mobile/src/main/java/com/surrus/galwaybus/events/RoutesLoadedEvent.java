package com.surrus.galwaybus.events;

import com.surrus.galwaybus.model.BusRoute;

import java.util.ArrayList;

public class RoutesLoadedEvent {
    private ArrayList<BusRoute> busRoutes;

    public RoutesLoadedEvent(ArrayList<BusRoute> busRoutes) {
        this.busRoutes = busRoutes;
    }

    public ArrayList<BusRoute> getBusRoutes() {
        return busRoutes;
    }
}
