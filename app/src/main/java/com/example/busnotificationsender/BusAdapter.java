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
        boolean convertViewWasNull = false;
        if(busListItemView == null) {
            busListItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            convertViewWasNull = true;
        }

        //        EditText to enter the Bus number
        Bus currentBus = getItem(position);
        EditText editText = (EditText) busListItemView.findViewById(R.id.busNumber);
        editText.setText(String.valueOf(currentBus.getBusNumber()));
        //be aware that you shouldn't do this for each call on getView, just once by listItem when convertView is null
//        editText.addTextChangedListener(new GenericTextWatcher(editText));
//        editText.setTag("EditTextAtPos : "+position);
//        Log.d(">>>>Tag > ", editText.getTag().toString());
        //        Used EraserRegular font for the bus-stop names
        Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/KGSecondChancesSolid.ttf");
        editText.setTypeface(typeFace);

        //        TextView for the destinations of the corresponding bus-number
        final TextView destinationsTextView = (TextView) busListItemView.findViewById(R.id.busDestinations);
        destinationsTextView.setText(currentBus.getDestinations());
        destinationsTextView.setTypeface(typeFace);
        return busListItemView;
    }

//    private class GenericTextWatcher implements TextWatcher {
//
//        private View view;
//        private GenericTextWatcher(View view) {
//            this.view = view;
//        }
//
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//
//        public void afterTextChanged(Editable editable) {
//
//            String text = editable.toString();
//            //save the value for the given tag :
//            BusAdapter.this.textValues.put(view.getTag().toString(), Integer.parseInt(editable.toString()));
//        }
//    }

////    you can implement a method like this one for each EditText with the list position as parameter :
//    public Integer getValueFromEditText(int position){
//        //here you need to recreate the id for the editText
//        Integer result = textValues.get("EditTextAtPos:"+position);
//        return result;
//    }
}
