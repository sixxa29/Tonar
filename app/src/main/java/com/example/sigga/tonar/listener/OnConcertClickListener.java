package com.example.sigga.tonar.listener;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sigga.tonar.MainActivity;
import com.example.sigga.tonar.R;
import com.example.sigga.tonar.service.MidiConcertsService;


/**
 * Created by sigga on 19.10.2015.
 */
public class OnConcertClickListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Context context = view.getContext();

        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewItem));

        String listItemText = textViewItem.getText().toString();

        String listItemId = textViewItem.getTag().toString();

        Toast.makeText(context, "Item: " + listItemText + ", Item ID: " + listItemId, Toast.LENGTH_SHORT).show();

       // ((MainActivity) context).alertDialogStores.cancel();

    }

}