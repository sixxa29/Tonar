package com.example.sigga.tonar.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sigga on 8.10.2015.
 */
public class MidiConcertsService {
    private MidiConcertsCallback callback;
    private Exception error;
    private ArrayAdapterConcert mConcertAdapter;
    private AlertDialog alertDialogStores;
    private ArrayList<Results> results = new ArrayList<Results>();
    private Activity m;


    public MidiConcertsService(MidiConcertsCallback callback ) {
        this.callback = callback;
    }


    public void getData(final int viewId, final Activity mactivity) {
        this.m = mactivity;
        //alertDialogStores1 = this.alertDialogStores;
        Log.i("getData", "fyrir allt");
        new AsyncTask<String, Void, String>() {
            /*We will be making the API call in a separate thread, which is always a good
            practice since the user interface will not be blocked while the call is being made.
            This is especially important in mobile devices that may drop network connections
            or experience high network latency. To accomplish this, we will implement an
            AsyncTask subclass.*/

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
                        Results result = new Results(   concerts.getString("eventDateName"),
                                concerts.getString("name"),
                                concerts.getString("dateOfShow"),
                                concerts.getString("userGroupName"),
                                concerts.getString("eventHallName"),
                                concerts.getString("imageSource"), i);

                        results.add(result);
                    }

                }catch (JSONException e){
                    callback.serviceFail(e);
                }

                Log.i("onPostExecute", "eftr allt");
                mConcertAdapter = new ArrayAdapterConcert(mactivity, viewId, results);
                ListView listViewItems = new ListView(mactivity);
                listViewItems.setAdapter(mConcertAdapter);
                listViewItems.setOnItemClickListener(new OnConcertClickListener2());
                alertDialogStores = new AlertDialog.Builder(mactivity)
                        .setView(listViewItems)
                        .setTitle("Allir tónleikar")
                        .show();

            }

        }.execute();
        Log.i("getData", "eftr allt");
    }


    public class OnConcertClickListener2 implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            final Context context = view.getContext();

            Results r = results.get(position);
            final String eventDateName = r.getEventDateName();
            final String name = r.getName();
            final String dateOfShow = r.getDateOfShow();
            String userGroupName = r.getUserGroupName();
            final String eventHallName = r.getEventHallName();

            alertDialogStores.cancel();

            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.single_concert);
            dialog.setTitle(eventDateName);

            TextView text = (TextView) dialog.findViewById(R.id.TonleikarTextview);
            text.setText(name);



            ImageView image = (ImageView) view.findViewById(R.id.imageView);
            ImageView img = (ImageView) dialog.findViewById(R.id.TonleikarImageView);
            img.setImageDrawable(image.getDrawable());
            View.OnClickListener handler = new View.OnClickListener() {
                public void onClick(View v){
                    addToCal( dateOfShow, eventDateName, eventHallName, name, position, m);
                }
            };
            dialog.findViewById(R.id.eventAddButton).setOnClickListener(handler);

            dialog.show();


            //alertDialogStores.cancel();

        }


        public void addToCal(String dateInString, String eventDateName, String eventHallName, String name, int pos, Activity mactivity ){

            String year = dateInString.split("-")[0];
            String month = dateInString.split("-")[1];
            String dayplus = dateInString.split("-")[2];
            String day = dayplus.split("T")[0];
            String hourminute = dateInString.split("T")[1];
            String hour = hourminute.split(":")[0];
            String minute = hourminute.split(":")[1];



            //"YY:MM:dd:hh:mm"
            Calendar beginTime = Calendar.getInstance();
            beginTime.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minute));
            long startMillis = beginTime.getTimeInMillis();
            Calendar endTime = Calendar.getInstance();
            endTime.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day), Integer.parseInt(hour) + 2, Integer.parseInt(minute));
            long endMillis = endTime.getTimeInMillis();


            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                    .putExtra(CalendarContract.Events.TITLE, eventDateName)
                    .putExtra(CalendarContract.Events.DESCRIPTION, name)
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, eventHallName)
                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
            mactivity.startActivity(intent);



        }
    }

    public class ConcertException extends Exception{
        public ConcertException(String detailMessage){
            super(detailMessage);
        }
    }





}