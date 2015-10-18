package com.example.sigga.tonar.rest;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.example.sigga.tonar.Model.Concert;

import java.util.List;

/**
 * Created by sigga on 17.10.2015.
 */
public class MidiQuery {
    private static MidiQuery instance = null;
    private static AsyncTask<Void, Void, Concert> concertTask = null;
    private static AsyncTask<Void, Void, List<Bitmap>> imageTask = null;

    public static MidiQuery getInstance(){
        if(instance == null){
            instance = new MidiQuery();
        }

        return instance;
    }

    public void getConcert(){
        Log.i("MidiQuery", "getConcert");
        final String url = "http://apis.is/concerts";

        concertTask = new ConcertTask(url);
        concertTask.execute();

    }
    /*
    public void cancelConcertTask(){
        if(concertTask != null){
            concertTask.cancel(true);
        }
    }*/


}
