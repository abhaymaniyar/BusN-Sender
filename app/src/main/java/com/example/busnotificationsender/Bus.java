package com.example.busnotificationsender;

/**
 * Created by abhay on 6/4/17.
 */

public class Bus {
    private int busNumber;
    private String destinations;

    public Bus(String destinations, int busNumber) {
        this.busNumber = busNumber;
        this.destinations = destinations;
    }

    public int getBusNumber() {
        return busNumber;
    }

    public String getDestinations() {
        return destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }
}
