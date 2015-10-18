package com.example.sigga.tonar.rest;


import com.example.sigga.tonar.Model.Concert;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.util.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


/**
 * Created by sigga on 18.10.2015.
 */
class ConcertTask extends AsyncTask<Void, Void, Concert> {


    private final String url;
    private HttpEntity entity;
    private RestTemplate restTemplate;
    private Exception error;


    ConcertTask(String url){
        this.url = url;
    }
    @Override
    protected Concert doInBackground(Void... params) {
        Log.i("ConcertTask", "DoInBackground !.");
        Concert concert = new Concert();
        System.setProperty("http.keepAlive", "false");
        while (true) {
            try {
                restTemplate = new RestTemplate();
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.set("Accept-Encoding", "");

                requestHeaders.set("Connection", "Close");
                requestHeaders.add("Content-Type", "application/text; charset=utf-8");
                System.out.println(requestHeaders);
                entity = new HttpEntity(requestHeaders);

                HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
                //factory.setConnectTimeout(connectionTimeOutSeconds * 1000);
                //factory.setReadTimeout(readTimeOutSeconds * 1000);
                restTemplate.setRequestFactory(factory);
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                Log.i("ConcertTask", "HTTP ENTITITY CONNECT TO APIS.");
                HttpEntity<String> response = connectToApis();
                Log.i("ConcertTask", "HTTP EFTIR CONNECT");
                concert = parseJson(response);

            } catch (Exception e) {
                /*String msg = "Failed receiving json from: " + url + ". NestedException is: " + e.getMessage();
                exception = new RestQueryException(msg, e);
                if (++howManyRetries == maxRetries) {
                    Log.i("CarTask", "Tried " + maxRetries + " times to send GET request.");
                    return car;
                }*/
                error = e;
            }
            // If nothing went wrong return the car. If exception != null then something went wrong.

            // ATH ATH ATH ATH ATH !!!!!!!! ERROR == NULL þeir eru með exception == null
            if (error == null) {
                return concert;
           }
            Log.i("ConcertTask", "Trying GET request again.");
        }
    }

    private HttpEntity<String> connectToApis() {
        HttpEntity<String> response = restTemplate.exchange( url, HttpMethod.GET, entity, String.class );
        return response;
    }

    private Concert parseJson(HttpEntity<String> respsonse){
        String jsonString = respsonse.getBody();
        Concert concert = new Concert();

        try{
            // jsonString = toUTF8 !! something something

            JSONObject jsonRoot = new JSONObject(jsonString);
            JSONArray jsonResult = (JSONArray) jsonRoot.get("results");

            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject jsonConcert = (JSONObject) jsonResult.get(i);
                concert.setEventDateName(jsonConcert.get("eventDateName").toString());
                concert.setName(jsonConcert.get("name").toString());
                concert.setDateOfShow(jsonConcert.get("dateOfShow").toString());
                concert.setUserGroupName(jsonConcert.get("userGroupName").toString());
                concert.setEventHallName(jsonConcert.get("eventHallName").toString());
                concert.setImageSource(jsonConcert.get("imageSource").toString());
            }


        }
        catch (JSONException e){
            error = e;
        }

        return concert;
    }

}
