package com.example.flightsearchdemo.request;

import com.example.flightsearchdemo.datamodel.JsonApiData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("")
    Call<JsonApiData> getData();
}
