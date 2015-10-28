package com.example.sigga.tonar;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


import com.example.sigga.tonar.DB.ConcertDB;
import com.example.sigga.tonar.DB.DbHelper;
import com.example.sigga.tonar.data.EventDateName;
import com.example.sigga.tonar.data.Result;
import com.example.sigga.tonar.data.Results;
import com.example.sigga.tonar.fragment.MasterFragment;
import com.example.sigga.tonar.service.MidiConcertsCallback;
import com.example.sigga.tonar.service.MidiConcertsService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MidiConcertsCallback, MasterFragment.OnButtonSelectedListeners {


    private TextView tonleikarTextView;
    private TextView nameTextView;
    private MidiConcertsService service;
    public AlertDialog alertDialogStores;
    private DbHelper m_dbHelper;
    private SQLiteDatabase m_db;
    private ArrayList<Results> r;
    private Cursor m_cursor;
    private SimpleCursorAdapter m_ca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);
        service = new MidiConcertsService(this);

        MasterFragment firstFragment = new MasterFragment();
        firstFragment.setArguments(getIntent().getExtras());
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, firstFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onButtonSelected(int position) {
        service.getData( R.layout.list_view_row_item, MainActivity.this);
        //r = service.getResults();
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
