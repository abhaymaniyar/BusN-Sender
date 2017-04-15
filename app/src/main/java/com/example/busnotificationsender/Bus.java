package com.example.busnotificationsender;

/**
 * Created by abhay on 6/4/17.
 */

public class Bus {
    private int busNumber;
    private String destinations;

    public Bus(){
        // Default constructor required for calls to DataSnapshot.getValue(Bus.class)
    }

    public Bus(String destinations, int busNumber) {
        this.destinations = destinations;
        this.busNumber = busNumber;
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
