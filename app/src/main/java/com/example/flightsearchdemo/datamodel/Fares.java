package com.example.flightsearchdemo.datamodel;

public class Fares {

    private String providerId;
    private String fare;

    public Fares(int providerId, int fare) {
        this.providerId = "" + providerId;
        this.fare = "" + fare;
    }

    public String getFare() {
        return fare;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setFare(int fare) {
        this.fare = "" + fare;
    }

    public void setProviderId(int providerId) {
        this.providerId = "" + providerId;
    }
}
