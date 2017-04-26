package com.example.busnotificationsender;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Buses", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        loadSavedPreferences();

        buses = new ArrayList<Bus>();
        buses.add(new Bus(getResources().getString(R.string.AgnibaanKhajaranaRaniSati), sharedPreferences.getInt("Agnibaan~Khajarana~Rani_Sati", 0)));
        buses.add(new Bus(getResources().getString(R.string.BhavarkuaV), sharedPreferences.getInt("Bhavarkua-V", 0)));
        buses.add(new Bus(getResources().getString(R.string.BoysHostelTeenImliKhajarana), sharedPreferences.getInt("Boys_hostel~teen_imli~Khajarana", 0)));
        buses.add(new Bus(getResources().getString(R.string.DevGuradiyaIndustryHousePalasia), sharedPreferences.getInt("Dev_Guradiya~Industry_House~Palasia", 0)));
        buses.add(new Bus(getResources().getString(R.string.IndustryHouseRaniSatiAgnibaanKhajrana), sharedPreferences.getInt("Industry_House~Rani_Sati~Agnibaan~Khajrana", 0)));
        buses.add(new Bus(getResources().getString(R.string.Kalani_Naga), sharedPreferences.getInt("Kalani_Naga", 0)));
        buses.add(new Bus(getResources().getString(R.string.KhandwaNakaShalimaarScheme54), sharedPreferences.getInt("Khandwa_naka~Shalimaar~Scheme_54", 0)));
        buses.add(new Bus(getResources().getString(R.string.KhativalaRaniSatiAgnibaanKhajrana), sharedPreferences.getInt("Khativala~Rani_Sati~Agnibaan~Khajrana", 0)));
        buses.add(new Bus(getResources().getString(R.string.PardesipuraVijaynagar), sharedPreferences.getInt("Pardesipura~Vijaynagar", 0)));
        buses.add(new Bus(getResources().getString(R.string.RaniSatiAgnibaanKhajranaRaniSati), sharedPreferences.getInt("Rani_Sati~Agnibaan~Khajrana~Rani_sati", 0)));
        buses.add(new Bus(getResources().getString(R.string.SaiMandirVijayNagar), sharedPreferences.getInt("Sai_Mandir~VijayNagar", 0)));
        final BusAdapter busAdapter = new BusAdapter(this, buses);

        // Get a reference to the ListView, and attach the adapter to the listView.
        listView.setAdapter(busAdapter);


        //sends RealtimeDatabase write operation to Firebase database and updates the EditTexts and shows a toast
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference bdRef = mDatabase.child("Buses");
                for (int i = 0; i < buses.size(); i++) {
                    EditText editText = (EditText) getViewByPosition(i, listView).findViewById(R.id.busNumber);
                    TextView textView = (TextView) getViewByPosition(i, listView).findViewById(R.id.busDestinations);
                    Log.d(">>>", "onClick: "+bdRef.child(textView.getText().toString()).getKey().toString());
                    Log.d(">>>", "onClick: "+buses.get(i).getDestinations());
                    String busName = bdRef.child(textView.getText().toString()).getKey().toString();
                    if (busName.equals(buses.get(i).getDestinations())){
                        Log.d(">>>", "onClick: "+busName.equals(buses.get(i).getDestinations()));
                        bdRef.child(buses.get(i).getDestinations()).setValue(Integer.valueOf(editText.getText().toString()));
                        buses.get(i).setBusNumber(Integer.valueOf(editText.getText().toString()));
                        busAdapter.notifyDataSetChanged();
                    }
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
