package com.example.sigga.tonar.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sigga.tonar.MainActivity;
import com.example.sigga.tonar.R;
import com.example.sigga.tonar.adapter.ArrayAdapterConcert;
import com.example.sigga.tonar.data.Results;
import com.example.sigga.tonar.listener.OnConcertClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by sigga on 8.10.2015.
 */
public class MidiConcertsService {
    private MidiConcertsCallback callback;
    private Exception error;
    private ArrayAdapterConcert mConcertAdapter;
    private ArrayList<Results> results;


    public MidiConcertsService(MidiConcertsCallback callback) {
        this.callback = callback;
    }

    public ArrayList<Results> getResults(){
        return results;
    }

    public void setResults(ArrayList<Results> r){
        this.results = r;
    }


    public void getData(final int viewId, final Activity mactivity) {
        Log.i("getData", "fyrir allt");
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                Log.i("doInBackground", "fyrir allt");
                final String endpoint = "http://apis.is/concerts";

                try {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();

                } catch (Exception e) {
                    error = e;
                }
                Log.i("doInBackground", "eftir allt");
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                Log.i("onPostExecute", "fyrir allt");
                if(s == null && error != null){
                    callback.serviceFail(error);
                    return;
                }


                try{
                    Log.i("tryJson parsing", "fyrir allt");
                    JSONObject data = new JSONObject(s);
                    JSONArray queryResult = data.getJSONArray("results");

                    for(int i = 0; i < queryResult.length(); i++){
                        JSONObject concerts = queryResult.getJSONObject(i);
                        Results result = new Results(
                                concerts.getString("eventDateName"),
                                concerts.getString("name"),
                                concerts.getString("dateOfShow"),
                                concerts.getString("userGroupName"),
                                concerts.getString("eventHallName"),
                                concerts.getString("imageSource"));

                        results.add(result);
                    }

                }catch (JSONException e){
                    callback.serviceFail(e);
                }
                mConcertAdapter = new ArrayAdapterConcert(mactivity, viewId, results);
                ListView listViewItems = new ListView(mactivity);
                listViewItems.setAdapter(mConcertAdapter);
                //listViewItems.setOnItemClickListener(new OnConcertClickListener2());
            }

        }.execute();
        Log.i("getData", "eftr allt");
    }




    public class ConcertException extends Exception{
        public ConcertException(String detailMessage){
            super(detailMessage);
        }
    }
}