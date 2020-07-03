package com.example.flightsearchdemo.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://run.mocky.io/v3/5c8d6c99-c6d6-4dbd-9e39-a8b9c9d3e6d7/";
    private static Retrofit retrofit;
    private static RetrofitClient mInstance;

    private RetrofitClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public static GetDataService getApi() {
        return retrofit.create(GetDataService.class);
    }
}
