package com.example.busnotificationsender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Bus> buses = new ArrayList<Bus>();
        buses.add(new Bus("Pardesipura", 1));
        buses.add(new Bus("Sai_Mandir-", 2));
        buses.add(new Bus("Kalani_Naga", 2));
        buses.add(new Bus("Bhavarkua-V", 3));
        buses.add(new Bus("Sapna_Sange", 2));
        buses.add(new Bus("tilak_nagar", 0));
        buses.add(new Bus("Sai_Mandir-", 2));
        buses.add(new Bus("Kalani_Naga", 2));
        buses.add(new Bus("Bhavarkua-V", 3));
        buses.add(new Bus("Sapna_Sange", 2));
        buses.add(new Bus("tilak_nagar", 0));
//        buses.add(new Bus("देव्गुरदिया इंडस्टृी होउस पलासिया", 3));
//        buses.add(new Bus("शालीमार स्कीम खन्डवा नाका", 4));
//        buses.add(new Bus("तीन ईमली खजराना बोय्ज होस्टल", 1));
//        buses.add(new Bus("खातीवाला राजवाडा अग्निबाण रानी सती गेट", 0));

        BusAdapter busAdapter= new BusAdapter(this, buses);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.busListView);
        listView.setAdapter(busAdapter);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Notifications Sent", Toast.LENGTH_SHORT);
            }
        });
    }
}
