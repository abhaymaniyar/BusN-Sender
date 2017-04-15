package com.example.busnotificationsender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase =  FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Bus bus1 = new Bus("Vijaynagar", 32);
//        Map<String, Integer> buses = new HashMap<String, Integer>();
//        buses.put("Pardesipura", 1);
//        buses.put("Sai ", 11);
//        buses.put("Ram", 31);
//        buses.put("sapna", 311);
//        buses.put("asf", 1);
//        buses.put("Pardasdfesipura", 1);
//        buses.put("Pardesipura", 1);
//        buses.put("iii", 41);
//        buses.put("asdfasdf", 1);
//        buses.put("asdfasdfere", 31);
//

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
        BusAdapter busAdapter= new BusAdapter(this, buses);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.busListView);
        listView.setAdapter(busAdapter);

//        sends RealtimeDatabase write operation to Firebase database and shows a toast
        Button submitButton = (Button) findViewById(R.id.submit_button);
        EditText newBusNumber = (EditText) findViewById(R.id.busNumber);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DatabaseReference bdRef = mDatabase.child("Buses");
                    for (int i = 0; i < buses.size(); i++){
                        bdRef.child(buses.get(i).getDestinations()).setValue(buses.get(i).getBusNumber());
                    }
                Toast.makeText(getApplicationContext(), "Notifications Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
