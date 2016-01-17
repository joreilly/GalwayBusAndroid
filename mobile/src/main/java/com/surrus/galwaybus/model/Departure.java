package com.surrus.galwaybus.model;

public class Departure {
    private int timetable_id;
    private String display_name;
    private String depart_timestamp;

    public int getTimetableId() {
        return timetable_id;
    }

    public String getDisplayName() {
        return display_name;
    }

    public String getDepartTimestamp() {
        return depart_timestamp;
    }
}
