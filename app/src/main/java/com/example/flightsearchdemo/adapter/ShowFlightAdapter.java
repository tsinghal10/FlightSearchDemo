package com.example.flightsearchdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightsearchdemo.R;
import com.example.flightsearchdemo.databinding.ItemFlightBinding;
import com.example.flightsearchdemo.datamodel.Appendix;
import com.example.flightsearchdemo.datamodel.Fares;
import com.example.flightsearchdemo.datamodel.Flights;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.icu.text.DateFormat.LONG;

public class ShowFlightAdapter extends RecyclerView.Adapter<ShowFlightAdapter.ShowFlightViewHolder> {
    private ArrayList<Flights> flights;
    private Appendix appendix;
    private Context context;

    public ShowFlightAdapter(Context context, ArrayList<Flights> flights, Appendix appendix) {
        Log.d("adapter", "constructor");
        this.context = context;
        this.flights = flights;
        this.appendix = appendix;
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

        String airlines = flight.getAirlineCode();
        holder.itemFlightBinding.airlines.setText(appendix.getAirlines().get(airlines));
        holder.itemFlightBinding.classType.setText(flight.getClassType());
        ArrayList<Fares> fares = flight.getFares();
//        Log.d("Fare size", String.valueOf(fares.get(0).getFare()));
//        Log.d("Fare", flight.getClassType());

        int providerId = fares.get(0).getProviderId();
        holder.itemFlightBinding.fare.setText("\u20B9 " + String.valueOf(fares.get(0).getFare()));
        holder.itemFlightBinding.provider.setText(appendix.getProviders().get(String.valueOf(providerId)));

        Date departureDate = new Date(flight.getDepartureTime());
        Date arrivalDate = new Date(flight.getArrivalTime());
//        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
//        String date = DateFormat.getDateInstance(DateFormat.LONG).format(departureDate);
        String arrivalTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(arrivalDate);
        String departureTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(departureDate);
        holder.itemFlightBinding.time.setText(departureTime + " - " + arrivalTime);

        int noOfChildTextViews = holder.childLL.getChildCount();
        for (int index = 0; index < noOfChildTextViews; index++) {
            TextView fare = (TextView) holder.childLL.getChildAt(index);
//            TextView provider = (TextView) holder.childLL.getChildAt(index - 100);
//            Log.d("IDDDD: ", String.valueOf(provider.getId()));

            fare.setVisibility(View.VISIBLE);
//            provider.setVisibility(View.VISIBLE);
        }
        int noOfChild = flight.getFares().size();
        if (noOfChild < noOfChildTextViews) {
            for (int index = noOfChild; index < noOfChildTextViews; index++) {
                TextView fare = (TextView) holder.childLL.getChildAt(index);
//                TextView provider = (TextView) holder.childLL.getChildAt(index - 100);
                fare.setVisibility(View.GONE);
//                provider.setVisibility(View.GONE);
            }
        }
        for (int textViewIndex = 0; textViewIndex < noOfChild; textViewIndex++) {
            TextView fare = (TextView) holder.childLL.getChildAt(textViewIndex);
//            TextView provider = (TextView) holder.childLL.getChildAt(textViewIndex - 100);
            fare.setText("\u20B9 " + fares.get(textViewIndex).getFare() + "\t\t\t" + appendix.getProviders().get(String.valueOf(fares.get(textViewIndex).getProviderId())));
//            provider.setText(appendix.getProviders().get(String.valueOf(fares.get(textViewIndex).getProviderId())));
                /*currentTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "" + ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });*/
        }
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public void setAdapterData(ArrayList<Flights> flights, Appendix appendix) {
        this.flights = flights;
        this.appendix = appendix;
        notifyDataSetChanged();
    }

    public class ShowFlightViewHolder extends RecyclerView.ViewHolder {

        private ItemFlightBinding itemFlightBinding;
        private LinearLayout childLL;

        public ShowFlightViewHolder(@NonNull ItemFlightBinding itemFlightBinding) {
            super(itemFlightBinding.getRoot());
            Log.d("View Holder", "constructor");
            this.itemFlightBinding = itemFlightBinding;
            childLL = itemFlightBinding.linearChildLl;
            childLL.setVisibility(View.GONE);
            int intMaxNoOfChild = 0;
            for (int index = 0; index < flights.size(); index++) {
                int intMaxSizeTemp = flights.get(index).getFares().size();
                if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp;
            }
            for (int indexView = 0; indexView < intMaxNoOfChild; indexView++) {
                TextView price = new TextView(context);
//                TextView provider = new TextView(context);
                price.setId(indexView);
//                provider.setId(indexView - 100);
//                Log.d("ID: ", String.valueOf(provider.getId()));
                price.setPadding(0, 20, 0, 20);
//                provider.setPadding(0, 20, 0, 20);
                price.setGravity(Gravity.CENTER_HORIZONTAL);
//                provider.setGravity(Gravity.CENTER_HORIZONTAL);
//                textView.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
//                textView.setOnClickListener(this);
                childLL.addView(price, layoutParams);
//                childLL.addView(provider, layoutParams);
            }
            itemFlightBinding.showButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Log.d("ID1:", String.valueOf(view.getId()));
//                    Log.d("ID2:", String.valueOf(R.id.showButton));

                    if (view.getId() == R.id.showButton) {
                        if (childLL.getVisibility() == View.VISIBLE) {
                            childLL.setVisibility(View.GONE);
                        } else {
                            childLL.setVisibility(View.VISIBLE);
                        }
                    }
//                    } else {
//                        TextView textViewClicked = (TextView) view;
//                        Toast.makeText(context, "" + textViewClicked.getText().toString(), Toast.LENGTH_SHORT).show();
//                    }
                }
            });
        }

    }
}
