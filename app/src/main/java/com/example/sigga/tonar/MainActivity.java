package com.example.sigga.tonar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import com.example.sigga.tonar.data.EventDateName;
import com.example.sigga.tonar.data.Result;
import com.example.sigga.tonar.data.Results;
import com.example.sigga.tonar.formats.AlertMe;
import com.example.sigga.tonar.service.MidiConcertsCallback;
import com.example.sigga.tonar.service.MidiConcertsService;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MidiConcertsCallback {
    TextView tonleikarTextView;
    private MidiConcertsService service;
    private AlertMe alertMe = new AlertMe();
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
                        if(isConnected(v.getContext())){
                            service.getData(R.layout.list_view_row_item, MainActivity.this, 1);
                        }
                        else{
                            alertMe.canContinue(v.getContext(), "Hægan, hægan, ekkert wifi !");
                        }
                        break;
                    case R.id.buttonShowPopUp2:
                        if (isConnected(v.getContext())){
                            service.getData(R.layout.list_view_row_item, MainActivity.this, 2);
                        }
                        else{
                            alertMe.canContinue(v.getContext(), "Hægan, hægan, ekkert wifi !");
                        }
                        break;
                }
            }
        };
        findViewById(R.id.buttonShowPopUp).setOnClickListener(handler);
        findViewById(R.id.buttonShowPopUp2).setOnClickListener(handler);
    }
    private static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean is3g = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();
        boolean isWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();

        if (!is3g && !isWifi)
        {
            return false;
        }
        else
        {
            return true;
        }
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