package com.example.busnotificationsender;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abhay on 6/4/17.
 */

public class BusAdapter extends ArrayAdapter<Bus> {

    private HashMap<String, Integer> textValues = new HashMap<String, Integer>();

    public BusAdapter(Activity context, ArrayList<Bus> buses){
        super(context, 0, buses);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View busListItemView = convertView;
        if(busListItemView == null) {
            busListItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //        EditText to enter the Bus number
        Bus currentBus = getItem(position);
        EditText editText = (EditText) busListItemView.findViewById(R.id.busNumber);
        editText.setText(String.valueOf(currentBus.getBusNumber()));

        Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/KGSecondChancesSolid.ttf");
        editText.setTypeface(typeFace);

        //        TextView for the destinations of the corresponding bus-number
        final TextView destinationsTextView = (TextView) busListItemView.findViewById(R.id.busDestinations);
        destinationsTextView.setText(currentBus.getDestinations());
        destinationsTextView.setTypeface(typeFace);
        return busListItemView;
    }
}
