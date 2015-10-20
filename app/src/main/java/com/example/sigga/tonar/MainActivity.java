package com.example.sigga.tonar;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.app.AlertDialog;


import com.example.sigga.tonar.data.EventDateName;
import com.example.sigga.tonar.data.ObjectItem;
import com.example.sigga.tonar.data.Result;
import com.example.sigga.tonar.service.MidiConcertsCallback;
import com.example.sigga.tonar.service.MidiConcertsService;

public class MainActivity extends AppCompatActivity implements MidiConcertsCallback {

    private TextView tonleikarTextView;
    private TextView nameTextView;
    private MidiConcertsService service;
    public AlertDialog alertDialogStores;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = new MidiConcertsService(this);
        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.buttonShowPopUp:
                        Log.i("onCreate", "fyrir allt");
                        service.getData(R.layout.list_view_row_item, MainActivity.this);
                        Log.i("onCreate", "eftir allt");
                        break;
                }

            }
        };

        findViewById(R.id.buttonShowPopUp).setOnClickListener(handler);


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
