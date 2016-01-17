package com.surrus.galwaybus.events;

import com.surrus.galwaybus.model.BusStop;

import java.util.List;

public class StopsLoadedEvent {
    private List<List<BusStop>> busStops;

    public StopsLoadedEvent(List<List<BusStop>> busStops) {
        this.busStops = busStops;
    }

    public List<List<BusStop>>  getBusStops() {
        return busStops;
    }
}
