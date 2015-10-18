package com.example.sigga.tonar.service;

import android.os.AsyncTask;
import com.example.sigga.tonar.data.Result;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sigga on 8.10.2015.
 */
public class MidiConcertsService {
    private MidiConcertsCallback callback;
    private Exception error;
    private String name;


    public MidiConcertsService(MidiConcertsCallback callback) {
        this.callback = callback;
    }

    public String getName() {
        return name;
    }

    public void getData() {
        //this.name = n;

        new AsyncTask<String, Void, String>() {

            /*We will be making the API call in a separate thread, which is always a good
            practice since the user interface will not be blocked while the call is being made.
            This is especially important in mobile devices that may drop network connections
            or experience high network latency. To accomplish this, we will implement an
            AsyncTask subclass.*/

            @Override
            protected String doInBackground(String... params) {

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
                    JSONObject queryResult = data.getJSONObject("query");
                    int count = queryResult.optInt("count");

                    if(count == 0){
                        callback.serviceFail(new LocationWeatherException("Engir tónleikar með þessu nafni " + name));
                        return;
                    }

                    Result result = new Result();
                    result.populate(queryResult.optJSONObject("results").optJSONObject("eventDateName"));

                    callback.serviceSuccess(result);

                }catch (JSONException e){
                    callback.serviceFail(e);
                }
            }
        }.execute();
    }

    public class LocationWeatherException extends Exception{
        public LocationWeatherException(String detailMessage){
            super(detailMessage);
        }
    }
}