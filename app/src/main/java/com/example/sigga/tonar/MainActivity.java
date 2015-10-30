package com.example.sigga.tonar;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


import com.example.sigga.tonar.DB.DbHelper;
import com.example.sigga.tonar.data.EventDateName;
import com.example.sigga.tonar.data.Result;
import com.example.sigga.tonar.service.MidiConcertsCallback;
import com.example.sigga.tonar.service.MidiConcertsService;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements MidiConcertsCallback {

    private TextView tonleikarTextView;
    private TextView nameTextView;
    private MidiConcertsService service;
    public AlertDialog alertDialogStores;
    private DbHelper m_dbHelper;
    private SQLiteDatabase m_db;

    private Cursor m_cursor;
    private SimpleCursorAdapter m_ca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        service = new MidiConcertsService(this);
        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.buttonShowPopUp:
                        service.getData(R.layout.list_view_row_item, MainActivity.this, 1);
                        break;
                    case R.id.buttonShowPopUp2:
                        service.getData(R.layout.list_view_row_item, MainActivity.this, 2);
                        break;
                }

            }
        };

        findViewById(R.id.buttonShowPopUp).setOnClickListener(handler);
        findViewById(R.id.buttonShowPopUp2).setOnClickListener(handler);


    }


    @Override
    public void serviceSuccess(Result result) {

        EventDateName event = result.getEventDateName();
        Log.i("serviceSuccess", "eftir event dæmið");

        tonleikarTextView.setText(event.getEventDateName());

    }

    @Override
    public void serviceFail(Exception ex) {
        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
    }


}
