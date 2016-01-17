package com.surrus.galwaybus.model;

import java.util.List;

public class GetDeparturesResponse {

    private BusStop stop;
    private List<Departure> times;

    public BusStop getStop() {
        return stop;
    }

    public List<Departure> getDepartureTimes() {
        return times;
    }
}
