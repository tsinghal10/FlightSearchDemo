package com.example.flightsearchdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightsearchdemo.databinding.ItemFlightBinding;
import com.example.flightsearchdemo.datamodel.Fares;
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
        Log.d("adapter", "constructor");
        this.context = context;
        this.flights = flights;
    }

    @NonNull
    @Override
    public ShowFlightAdapter.ShowFlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("adapter", "create view holder");
        ItemFlightBinding itemFlightBinding = ItemFlightBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ShowFlightViewHolder(itemFlightBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowFlightViewHolder holder, int position) {
        Log.d("adapter", "bind view holder");
        Flights flight = flights.get(position);
        holder.itemFlightBinding.airlines.setText(flight.getAirlineCode());
        holder.itemFlightBinding.classType.setText(flight.getClassType());
        ArrayList<Fares> fares = flight.getFares();
//        Log.d("Fare size", String.valueOf(fares.get(0).getFare()));
//        Log.d("Fare", flight.getClassType());
        holder.itemFlightBinding.fare.setText(String.valueOf(fares.get(0).getFare()));
        holder.itemFlightBinding.provider.setText(String.valueOf(fares.get(0).getProviderId()));

        Date departureDate = new Date(flight.getDepartureTime());
        Date arrivalDate = new Date(flight.getArrivalTime());
//        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String date = DateFormat.getDateInstance(DateFormat.LONG).format(departureDate);
        String arrivalTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(arrivalDate);
        String departureTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(departureDate);
        holder.itemFlightBinding.time.setText(date + "\n" + departureTime + " - " + arrivalTime);
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public void setAdapterData(ArrayList<Flights> flights) {
        this.flights = flights;
        notifyDataSetChanged();
    }

    public class ShowFlightViewHolder extends RecyclerView.ViewHolder {

        private ItemFlightBinding itemFlightBinding;

        public ShowFlightViewHolder(@NonNull ItemFlightBinding itemFlightBinding) {
            super(itemFlightBinding.getRoot());
            Log.d("View Holder", "constructor");
            this.itemFlightBinding = itemFlightBinding;
        }

    }
}
