package com.example.flightsearchdemo.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flightsearchdemo.datamodel.JsonApiData;
import com.example.flightsearchdemo.request.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFlightViewModel extends ViewModel {

    private MutableLiveData<JsonApiData> mJsonData;

    public LiveData<JsonApiData> getData() {
        if (mJsonData == null) {
            mJsonData = new MutableLiveData<>();
            loadData();
        }
        return mJsonData;
    }

    private void loadData() {

        RetrofitClient
                .getInstance();
        Call<JsonApiData> call = RetrofitClient
                .getApi()
                .getData();

        call.enqueue(new Callback<JsonApiData>() {
            @Override
            public void onResponse(Call<JsonApiData> call, Response<JsonApiData> response) {
                if (!response.isSuccessful()) {
                    Log.d("API CALL", "Call Unsuccessful");
                } else {
                    JsonApiData jsonApiData = response.body();
                    Log.d("API CALL", "Call Successful");
                    mJsonData.postValue(jsonApiData);
                }
            }

            @Override
            public void onFailure(Call<JsonApiData> call, Throwable t) {
                Log.d("API CALL", t.getMessage());
            }
        });

    }
}
