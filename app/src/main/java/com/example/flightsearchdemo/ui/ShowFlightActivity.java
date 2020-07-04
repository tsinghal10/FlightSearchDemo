package com.example.flightsearchdemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.flightsearchdemo.databinding.ActivityShowFlightBinding;
import com.example.flightsearchdemo.viewmodel.ShowFlightViewModel;

public class ShowFlightActivity extends AppCompatActivity {
    private ActivityShowFlightBinding binding;
    private ShowFlightViewModel showFlightViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowFlightBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String origin = getIntent().getStringExtra("origin");
        String destination = getIntent().getStringExtra("destination");

        binding.to.setText(origin);
        binding.from.setText(destination);

        showFlightViewModel = new ViewModelProvider(this).get(ShowFlightViewModel.class);
        showFlightViewModel.getData().observe(this, mJsonData -> {

        });
    }
}