package com.surrus.galwaybus.model;

public class BusStop {
    private int stop_id;
    private String short_name;
    private String long_name;
    private String stop_ref;
    private String irish_short_name;
    private String irish_long_name;
    private double latitude;
    private double longitude;


    public int getStopId() {
        return stop_id;
    }

    public String getShortName() {
        return short_name;
    }

    public String getLongName() {
        return long_name;
    }

    public String getStopRef() {
        return stop_ref;
    }

    public String getIrishShortName() {
        return irish_short_name;
    }

    public String getIrishLongName() {
        return irish_long_name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
