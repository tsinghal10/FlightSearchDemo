package com.example.flightsearchdemo.datamodel;

public class Fares {

    private int providerId;
    private int fare;

    public Fares(int providerId, int fare) {
        this.providerId = providerId;
        this.fare = fare;
    }

    public int getFare() {
        return fare;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }
}
