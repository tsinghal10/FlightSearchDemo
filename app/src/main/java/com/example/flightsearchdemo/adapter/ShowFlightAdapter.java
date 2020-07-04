package com.example.flightsearchdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightsearchdemo.databinding.ItemFlightBinding;
import com.example.flightsearchdemo.datamodel.Flights;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.icu.text.DateFormat.LONG;

public class ShowFlightAdapter extends RecyclerView.Adapter<ShowFlightAdapter.ShowFlightViewHolder> {
    private ArrayList<Flights> flights;
    private Context context;

    public ShowFlightAdapter(Context context, ArrayList<Flights> flights) {
        this.context = context;
        this.flights = flights;
    }

    @NonNull
    @Override
    public ShowFlightAdapter.ShowFlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFlightBinding itemFlightBinding = ItemFlightBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ShowFlightViewHolder(itemFlightBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowFlightViewHolder holder, int position) {
        Flights flight = flights.get(position);
        holder.itemFlightBinding.airlines.setText(flight.getAirlineCode());
        holder.itemFlightBinding.classType.setText(flight.getClassType());
        holder.itemFlightBinding.fare.setText(flight.getFares().get(0).getFare());
        holder.itemFlightBinding.provider.setText(flight.getFares().get(0).getProviderId());

        Date departureDate = new Date(flight.getDepartureTime());
        Date arrivalDate = new Date(flight.getArrivalTime());
//        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String arrivalTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(arrivalDate);
        String departureTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(departureDate);
        holder.itemFlightBinding.time.setText(departureTime + " - " + arrivalTime);
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public class ShowFlightViewHolder extends RecyclerView.ViewHolder {

        private ItemFlightBinding itemFlightBinding;

        public ShowFlightViewHolder(@NonNull ItemFlightBinding itemFlightBinding) {
            super(itemFlightBinding.getRoot());
            this.itemFlightBinding = itemFlightBinding;
        }

    }
}
