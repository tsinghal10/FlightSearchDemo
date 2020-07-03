package com.example.flightsearchdemo.datamodel;

import java.util.ArrayList;

public class Flights {

    private String originCode;
    private String destinationCode;
    private long departureTime;
    private long arrivalTime;
    private ArrayList<Fares> fares;
    private String airlineCode;
    private String classType;


    public Flights(String originCode, String destinationCode, long departureTime, long arrivalTime, ArrayList<Fares> fares, String airlineCode, String classType) {
        this.originCode = originCode;
        this.destinationCode = destinationCode;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.fares = fares;
        this.airlineCode = airlineCode;
        this.classType = classType;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public ArrayList<Fares> getFares() {
        return fares;
    }

    public void setFares(ArrayList<Fares> fares) {
        this.fares = fares;
    }
}
