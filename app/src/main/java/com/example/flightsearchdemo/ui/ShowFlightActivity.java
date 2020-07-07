package com.example.flightsearchdemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.flightsearchdemo.adapter.ShowFlightAdapter;
import com.example.flightsearchdemo.databinding.ActivityShowFlightBinding;
import com.example.flightsearchdemo.datamodel.Appendix;
import com.example.flightsearchdemo.datamodel.Fares;
import com.example.flightsearchdemo.datamodel.Flights;
import com.example.flightsearchdemo.viewmodel.ShowFlightViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class ShowFlightActivity extends AppCompatActivity {
    private ActivityShowFlightBinding binding;
    private ShowFlightViewModel showFlightViewModel;

    private RecyclerView recyclerView;
    private ShowFlightAdapter showFlightAdapter;

    private ArrayList<Flights> flights;
    private Appendix appendix;


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

        showFlightAdapter = new ShowFlightAdapter(this, flights, appendix);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(showFlightAdapter);

        showFlightViewModel = new ViewModelProvider(this).get(ShowFlightViewModel.class);
        showFlightViewModel.getData().observe(this, mJsonData -> {
            flights = mJsonData.getFlights();
            appendix = mJsonData.getAppendix();
            for (Flights f : flights) {
                if (f.getFares().size() > 1) {
                    Collections.sort(f.getFares(), PriceComparator);
                }
            }
            Date date = new Date(flights.get(0).getDepartureTime());
//            DateFormat timeFormat = new SimpleDateFormat("dd/MM/YY");
            String flightDate = DateFormat.getDateInstance(DateFormat.LONG).format(date);
            binding.date.setText(flightDate);

            Log.d("view model", "observer");
//            showFlightAdapter.notifyDataSetChanged();
            showFlightAdapter.setAdapterData(flights, appendix);
        });

        binding.arrivalTimeSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flights != null) {
                    Collections.sort(flights, ArrivalTimeComparator);
                    showFlightAdapter.setAdapterData(flights, appendix);
                }
            }
        });

        binding.departureTimeSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flights != null) {
                    Collections.sort(flights, DepartureTimeComparator);
                    showFlightAdapter.setAdapterData(flights, appendix);
                }
            }
        });

        binding.priceSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flights != null) {
                    Collections.sort(flights, FareComparator);
                    showFlightAdapter.setAdapterData(flights, appendix);
                }
            }
        });
    }

    public static Comparator<Flights> ArrivalTimeComparator
            = new Comparator<Flights>() {
        public int compare(Flights f1, Flights f2) {
            return (int) (f1.getArrivalTime() - f2.getArrivalTime());
        }
    };

    public static Comparator<Flights> DepartureTimeComparator
            = new Comparator<Flights>() {
        @Override
        public int compare(Flights flights, Flights t1) {
            return (int) (flights.getDepartureTime() - t1.getDepartureTime());
        }
    };

    public static Comparator<Flights> FareComparator
            = new Comparator<Flights>() {
        @Override
        public int compare(Flights flights, Flights t1) {
            return flights.getFares().get(0).getFare() - t1.getFares().get(0).getFare();
        }
    };

    private static Comparator<Fares> PriceComparator
            = new Comparator<Fares>() {
        public int compare(Fares f1, Fares f2) {
            return f1.getFare() - f2.getFare();
        }
    };


}