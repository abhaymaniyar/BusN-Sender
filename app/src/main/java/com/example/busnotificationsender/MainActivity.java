package com.example.busnotificationsender;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    ListView listView;
    ArrayList<Bus> buses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.busListView);
        loadSavedPreferences();

        buses = new ArrayList<Bus>();
        buses.add(new Bus("देव्गुhjhhरदिया इंडस्टृी होउस पलासिया", 0));
        buses.add(new Bus("शालीमार kjkस्कीम खन्डवा नाका", 0));
        buses.add(new Bus("तीन dईमली खजराना बोय्ज होस्टल", 0));
        buses.add(new Bus("खातीवाला राजkjkवाडा अग्निबाण रानी सती गेट", 0));
        buses.add(new Bus("देव्गुरदिया इंडस्टृी होउस पलासिया", 0));
        buses.add(new Bus("शालीddमार स्कीम खन्डवा नाका", 0));
        buses.add(new Bus("तीन ईमली खजराना बोय्ज होस्टल", 0));
        buses.add(new Bus("खातीवाला राजवाडा अग्निबाण रानी सती गेट", 0));
        buses.add(new Bus("देव्गुरदdिया इंडस्टृी होउस पलासिया", 0));
        buses.add(new Bus("शालीमjkjार स्कीम खन्डवा नाका", 0));
        buses.add(new Bus("तीनd ईमली खजराना बोय्ज होस्टल", 0));
        buses.add(new Bus("खातीवdाला राजवाडा अग्निबाण रानी सती गेट", 0));
        final BusAdapter busAdapter = new BusAdapter(this, buses);

        // Get a reference to the ListView, and attach the adapter to the listView.
        listView.setAdapter(busAdapter);

//        sends RealtimeDatabase write operation to Firebase database and shows a toast
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference bdRef = mDatabase.child("Buses");
                for (int i = 0; i < buses.size(); i++) {
                    EditText editText = (EditText) getViewByPosition(i, listView).findViewById(R.id.busNumber);
                    bdRef.child(buses.get(i).getDestinations()).setValue(Integer.valueOf(editText.getText().toString()));
                }
                Toast.makeText(getApplicationContext(), "Notifications Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        for (int i = 0; i < listView.getCount(); i++){
            EditText editText = (EditText) listView.getChildAt(i).findViewById(R.id.busNumber);
            editText.setText(sharedPreferences.getInt(buses.get(i).getDestinations(), buses.get(i).getBusNumber()));
        }
    }

    private void savePreferences(String destinations, int number){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(destinations, number);
        editor.commit();
    }

    public void saveData(){
        for (int i = 0; i < buses.size(); i++){
            savePreferences(buses.get(i).getDestinations(), buses.get(i).getBusNumber());
        }
    }

    @Override
    public void onBackPressed(){
        saveData();
        super.onBackPressed();
    }

    //    method to get view from the listView
//    @param position, listView
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
