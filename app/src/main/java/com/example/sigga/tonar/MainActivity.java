package com.example.sigga.tonar;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sigga.tonar.data.EventName;
import com.example.sigga.tonar.data.Name;
import com.example.sigga.tonar.data.Result;
import com.example.sigga.tonar.rest.MidiQuery;
import com.example.sigga.tonar.service.ConcertService;
import com.example.sigga.tonar.service.MidiConcertsCallback;
import com.example.sigga.tonar.service.MidiConcertsService;

public class MainActivity extends AppCompatActivity implements MidiConcertsCallback {

    private TextView tonleikarTextView;
    private TextView nameTextView;
    private MidiQuery concert;
    //private MidiConcertsService service;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tonleikarTextView = (TextView)findViewById(R.id.tonleikarTextView);
        nameTextView = (TextView)findViewById(R.id.nameTextView);


        concert = new MidiQuery();
        concert.getConcert();
        
    }

    @Override
    public void serviceSuccess(Result result) {

        EventName event = result.getEvent();


        tonleikarTextView.setText(event.getEventName());
        //nameTextView.setText(service.getName());

    }

    @Override
    public void serviceFail(Exception ex) {
        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
    }


}
