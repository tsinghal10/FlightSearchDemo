package com.example.flightsearchdemo.request;

import com.example.flightsearchdemo.datamodel.JsonApiData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetDataService {

    @GET("flights")
    Call<JsonApiData> getData();
}
