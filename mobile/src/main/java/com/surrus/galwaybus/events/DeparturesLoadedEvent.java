package com.surrus.galwaybus.events;

import com.surrus.galwaybus.model.Departure;

import java.util.List;

public class DeparturesLoadedEvent {
    private List<Departure> departures;

    public DeparturesLoadedEvent(List<Departure> departures) {
        this.departures = departures;
    }

    public List<Departure> getDepartures() {
        return departures;
    }
}
