package com.example.flightsearchdemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.flightsearchdemo.adapter.ShowFlightAdapter;
import com.example.flightsearchdemo.databinding.ActivityShowFlightBinding;
import com.example.flightsearchdemo.datamodel.Flights;
import com.example.flightsearchdemo.viewmodel.ShowFlightViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShowFlightActivity extends AppCompatActivity {
    private ActivityShowFlightBinding binding;
    private ShowFlightViewModel showFlightViewModel;

    private RecyclerView recyclerView;
    private ShowFlightAdapter showFlightAdapter;

    private ArrayList<Flights> flights;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowFlightBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String origin = getIntent().getStringExtra("origin");
        String destination = getIntent().getStringExtra("destination");

        binding.to.setText(origin);
        binding.from.setText(destination);

        flights = new ArrayList<>();
        recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        showFlightAdapter = new ShowFlightAdapter(this, flights);
        recyclerView.setAdapter(showFlightAdapter);

        showFlightViewModel = new ViewModelProvider(this).get(ShowFlightViewModel.class);
        showFlightViewModel.getData().observe(this, mJsonData -> {
            flights = mJsonData.getFlights();

            Date date = new Date(flights.get(0).getDepartureTime());
//            DateFormat timeFormat = new SimpleDateFormat("dd/MM/YY");
            String flightDate = DateFormat.getDateInstance(DateFormat.LONG).format(date);
            binding.date.setText(flightDate);

            showFlightAdapter.notifyDataSetChanged();

        });
    }
}