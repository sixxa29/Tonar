package com.example.sigga.tonar.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sigga.tonar.MainActivity;
import com.example.sigga.tonar.R;
import com.example.sigga.tonar.adapter.ArrayAdapterConcert;
import com.example.sigga.tonar.data.EventDateName;
import com.example.sigga.tonar.data.Result;
import com.example.sigga.tonar.data.Results;
import com.example.sigga.tonar.service.MidiConcertsCallback;
import com.example.sigga.tonar.service.MidiConcertsService;

import java.util.ArrayList;

/**
 * Created by sigga on 26.10.2015.
 */
public class ConcertListFragment extends ListFragment{

    //MidiConcertsService s = new MidiConcertsService(this);
     ArrayList<Results> data;
  //  ArrayAdapterConcert arrayAdapterConcert = new ArrayAdapterConcert(getActivity(), R.layout.list_view_row_item, data);
    public final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;


    public void dataArr(ArrayList<Results> data){
        this.data = data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create an array adapter for the list view, using the Ipsum headlines array
        //s.getData();
        //data = s.results;
        setListAdapter(new ArrayAdapterConcert(getActivity(), R.layout.list_view_row_item, data));
    }


                /*
                Log.i("onPostExecute", "eftr allt");
                mConcertAdapter = new ArrayAdapterConcert(mactivity, viewId, results);
                ListView listViewItems = new ListView(mactivity);
                listViewItems.setAdapter(mConcertAdapter);
                listViewItems.setOnItemClickListener(new OnConcertClickListener());
                alertDialogStores = new AlertDialog.Builder(mactivity)
                        .setView(listViewItems)
                        .setTitle("Allir tónleikar")
                        .show();*/

    @Override
    public void onStart() {
        super.onStart();
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    /*   public void updateConcertView( int position, View view, ViewGroup parent ) {
        arrayAdapterConcert.getView(position, view, parent);
    }*/

}
