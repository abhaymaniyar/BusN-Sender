package com.example.busnotificationsender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Bus> buses = new ArrayList<Bus>();
        buses.add(new Bus("देव्गुरदिया इंडस्टृी होउस पलासिया", 0));
        buses.add(new Bus("शालीमार स्कीम खन्डवा नाका", 0));
        buses.add(new Bus("तीन dईमली खजराना बोय्ज होस्टल", 0));
        buses.add(new Bus("खातीवाला राजवाडा अग्निबाण रानी सती गेट", 0));
        buses.add(new Bus("देव्गुरदिया इंडस्टृी होउस पलासिया", 0));
        buses.add(new Bus("शालीddमार स्कीम खन्डवा नाका", 0));
        buses.add(new Bus("तीन ईमली खजराना बोय्ज होस्टल", 0));
        buses.add(new Bus("खातीवाला राजवाडा अग्निबाण रानी सती गेट", 0));
        buses.add(new Bus("देव्गुरदdिया इंडस्टृी होउस पलासिया", 0));
        buses.add(new Bus("शालीमार स्कीम खन्डवा नाका", 0));
        buses.add(new Bus("तीनd ईमली खजराना बोय्ज होस्टल", 0));
        buses.add(new Bus("खातीवdाला राजवाडा अग्निबाण रानी सती गेट", 0));
        final BusAdapter busAdapter = new BusAdapter(this, buses);

        // Get a reference to the ListView, and attach the adapter to the listView.
        final ListView listView = (ListView) findViewById(R.id.busListView);
        listView.setAdapter(busAdapter);

//        sends RealtimeDatabase write operation to Firebase database and shows a toast
        Button submitButton = (Button) findViewById(R.id.submit_button);
//        EditText newBusNumber = (EditText) findViewById(R.id.busNumber);
//        int i = Integer.valueOf(listView.getChildAt(2).findViewById(R.id.busNumber).getContentDescription().toString());
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference bdRef = mDatabase.child("Buses");
                for (int i = 0; i < buses.size(); i++) {
//                    int newBusNumber = Integer.valueOf(listView.getChildAt(2).findViewById(R.id.busNumber).getContentDescription().toString());
                    bdRef.child(buses.get(i).getDestinations()).setValue(busAdapter.getValueFromEditText(i).intValue());
                }
                Toast.makeText(getApplicationContext(), "Notifications Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public View getViewByPosition(int position, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (position < firstListItemPosition || position > lastListItemPosition) {
            return listView.getAdapter().getView(position, listView.getChildAt(position), listView);
        } else {
            final int childIndex = position - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }
}
