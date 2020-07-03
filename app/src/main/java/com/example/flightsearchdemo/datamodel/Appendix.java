package com.example.flightsearchdemo.datamodel;

import java.util.HashMap;

public class Appendix {
    private HashMap<String, String> airlines;
    private HashMap<String, String> airports;
    private HashMap<String, String> providers;

    public Appendix(HashMap<String, String> airlines, HashMap<String, String> airports, HashMap<String, String> providers) {
        this.airlines = airlines;
        this.airports = airports;
        this.providers = providers;
    }

    public HashMap<String, String> getAirlines() {
        return airlines;
    }

    public void setAirlines(HashMap<String, String> airlines) {
        this.airlines = airlines;
    }

    public HashMap<String, String> getAirports() {
        return airports;
    }

    public void setAirports(HashMap<String, String> airports) {
        this.airports = airports;
    }

    public HashMap<String, String> getProviders() {
        return providers;
    }

    public void setProviders(HashMap<String, String> providers) {
        this.providers = providers;
    }
}
