package com.example.sigga.tonar.service;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sigga.tonar.R;
import com.example.sigga.tonar.adapter.ArrayAdapterConcert;
import com.example.sigga.tonar.adapter.ArrayAdapterConcertByDay;
import com.example.sigga.tonar.data.Results;
import com.example.sigga.tonar.formats.AlertMe;
import com.example.sigga.tonar.formats.DateFormats;

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

public class MidiConcertsService {
    private MidiConcertsCallback callback;
    private Exception error;
    private ArrayAdapterConcert mConcertAdapter;
    private ArrayList<Results> results = new ArrayList<Results>();
    private Activity m;
    private AlertDialog alertDialogStores;
    private DateFormats formats = new DateFormats();
    private AlertMe alertMe = new AlertMe();
    public MidiConcertsService(MidiConcertsCallback callback ) {
        this.callback = callback;
    }
    public void getData(final int viewId, final Activity mactivity, final int btn) {
        this.m = mactivity;
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
                if(s == null && error != null){
                    callback.serviceFail(error);
                    return;
                }
                try{
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
                mConcertAdapter = new ArrayAdapterConcert(mactivity, viewId, results);
                ListView listViewItems = new ListView(mactivity);
                if(btn == 1){
                    listViewItems.setAdapter(mConcertAdapter);
                    listViewItems.setOnItemClickListener(new OnConcertClickListener2());
                    LayoutInflater inflater = mactivity.getLayoutInflater();
                    View view=inflater.inflate(R.layout.list_view_header, null);
                    ImageView back = (ImageView) view.findViewById(R.id.backButton);
                    alertDialogStores = new AlertDialog.Builder(mactivity)
                            .setView(listViewItems)
                            .show();
                }
                else if(btn == 2){
                    if(ConcertsToday(results).size() > 0){
                        ArrayAdapterConcertByDay mConcertAdapterByDay = new ArrayAdapterConcertByDay(mactivity, viewId, ConcertsToday(results));
                        listViewItems.setAdapter(mConcertAdapterByDay);
                        listViewItems.setOnItemClickListener(new OnConcertClickListener2());
                        alertDialogStores = new AlertDialog.Builder(mactivity)
                                .setView(listViewItems)
                                .show();
                    }
                    else{
                        alertMe.canContinue(mactivity, "Það eru engir tónleikar í dag, því miður...");
                    }
                    ConcertsToday(results).clear();

                }
            }
        }.execute();
    }
    public class OnConcertClickListener2 implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            Results r = results.get(position);
            final Context context = view.getContext();
            final String eventDateName = r.getEventDateName();
            final String name = r.getName();
            final String dateOfShow = r.getDateOfShow();
            final String eventHallName = r.getEventHallName();
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.single_concert);
            TextView concertName = (TextView) dialog.findViewById(R.id.ConcertName);
            TextView concertLocation = (TextView) dialog.findViewById(R.id.ConcertLocation);
            TextView concertDate = (TextView) dialog.findViewById(R.id.ConcertDate);
            TextView concertTime = (TextView) dialog.findViewById(R.id.ConcertTime);
            concertName.setText(eventDateName);
            concertLocation.setText(eventHallName);
            concertDate.setText(formats.getDateFormat(dateOfShow));
            concertTime.setText("Kl " + formats.getTimeFormat(dateOfShow));
            ImageView image = (ImageView) view.findViewById(R.id.imageView);
            ImageView img = (ImageView) dialog.findViewById(R.id.TonleikarImageView);
            img.setImageDrawable(image.getDrawable());
            dialog.show();
            View.OnClickListener handler2 = new View.OnClickListener() {
                public void onClick(View v){
                    dialog.dismiss();
                }
            };
            dialog.findViewById(R.id.backButton).setOnClickListener(handler2);
            View.OnClickListener handler = new View.OnClickListener() {
                public void onClick(View v){
                    addToCal( dateOfShow, eventDateName, eventHallName, name, m);
                }
            };
            dialog.findViewById(R.id.eventAddButton).setOnClickListener(handler);
        }
        public void addToCal(String dateInString, String eventDateName, String eventHallName, String name, Activity mactivity ){
            int year = Integer.parseInt(formats.getYear(dateInString));
            int month =  Integer.parseInt(formats.getMonth(dateInString));
            int day =  Integer.parseInt(formats.getDay(dateInString));
            int hour =  Integer.parseInt(formats.getHour(dateInString));
            int minute =  Integer.parseInt(formats.getMinute(dateInString));
            Calendar beginTime = Calendar.getInstance();
            beginTime.set(year, month-1, day, hour, minute);
            long startMillis = beginTime.getTimeInMillis();
            Calendar endTime = Calendar.getInstance();
            endTime.set(year, month-1, day, hour+2, minute);
            long endMillis = endTime.getTimeInMillis();
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
                    .putExtra(CalendarContract.Events.TITLE, eventDateName)
                    .putExtra(CalendarContract.Events.DESCRIPTION, name)
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, eventHallName)
                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
            mactivity.startActivity(intent);
        }
    }
    public ArrayList<Results> ConcertsToday (ArrayList<Results> results){
        ArrayList<Results> concertsToday = new ArrayList<Results>();
        concertsToday.removeAll(results);
        DateFormats forms = new DateFormats();
        for(int position = 0; position < results.size(); position++) {
            Results result = results.get(position);
            String dateInString = result.getDateOfShow();
            String fromAPI = forms.getDateFormat(dateInString);
            DateFormat df = new SimpleDateFormat("dd MMM yyyy");
            Date today = Calendar.getInstance().getTime();
            String todayString = df.format(today);
            String prufa = "29 Oct 2015";
            if (todayString.compareTo(fromAPI) == 0) {
                Results addToday = new Results(
                        result.getEventDateName(),
                        result.getName(),
                        result.getDateOfShow(),
                        result.getUserGroupName(),
                        result.getEventHallName(),
                        result.getImageSource(), position);
                concertsToday.add(addToday);
            }
        }
        return concertsToday;
    }
    public class ConcertException extends Exception{
        public ConcertException(String detailMessage){
            super(detailMessage);
        }
    }
}