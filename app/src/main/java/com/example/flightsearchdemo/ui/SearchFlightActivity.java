package com.example.flightsearchdemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.flightsearchdemo.databinding.ActivitySearchFlightBinding;
import com.example.flightsearchdemo.viewmodel.SearchFlightViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchFlightActivity extends AppCompatActivity {
    private ActivitySearchFlightBinding binding;
    private SearchFlightViewModel searchFlightViewModel;
    private HashMap<String, String> airports;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> airportsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchFlightBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        airportsList = new ArrayList<>();
        searchFlightViewModel = new ViewModelProvider(this).get(SearchFlightViewModel.class);
        searchFlightViewModel.getData().observe(this, mJsonData -> {
            airports = mJsonData.getAppendix().getAirports();
            for (Map.Entry me : airports.entrySet()) {
                airportsList.add(me.getKey() + " : " + me.getValue());
            }
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, airportsList);
            binding.origin.setAdapter(arrayAdapter);
            binding.origin.setThreshold(2);
            binding.destination.setAdapter(arrayAdapter);
            binding.destination.setThreshold(2);
        });

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (airportsList.contains(binding.origin.getText().toString()) && airportsList.contains(binding.destination.getText().toString())) {
                    Intent showIntent = new Intent(SearchFlightActivity.this, ShowFlightActivity.class);
                    showIntent.putExtra("origin", binding.origin.getText().toString());
                    showIntent.putExtra("destination", binding.destination.getText().toString());
                    startActivity(showIntent);
                } else {
                    Toast.makeText(getBaseContext(), "Invalid Data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}