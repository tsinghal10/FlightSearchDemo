package com.example.flightsearchdemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.flightsearchdemo.R;
import com.example.flightsearchdemo.databinding.ActivitySearchFlightBinding;
import com.example.flightsearchdemo.datamodel.JsonApiData;
import com.example.flightsearchdemo.viewmodel.SearchFlightViewModel;

public class SearchFlightActivity extends AppCompatActivity {
    private ActivitySearchFlightBinding binding;
    private SearchFlightViewModel searchFlightViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchFlightBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        searchFlightViewModel = new ViewModelProvider(this).get(SearchFlightViewModel.class);
        searchFlightViewModel.getData().observe(this, mJsonData -> {

        });
    }
}