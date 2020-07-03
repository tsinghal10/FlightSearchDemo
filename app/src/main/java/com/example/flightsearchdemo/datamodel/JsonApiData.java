package com.example.flightsearchdemo.datamodel;

import java.util.ArrayList;

public class JsonApiData {

    private Appendix appendix;
    private ArrayList<Flights> flights;


    public JsonApiData(Appendix appendix, ArrayList<Flights> flights) {
        this.appendix = appendix;
        this.flights = flights;
    }


    public Appendix getAppendix() {
        return appendix;
    }

    public void setAppendix(Appendix appendix) {
        this.appendix = appendix;
    }

    public ArrayList<Flights> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flights> flights) {
        this.flights = flights;
    }
}
