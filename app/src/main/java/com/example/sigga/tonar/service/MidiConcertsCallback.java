package com.example.sigga.tonar.service;

import com.example.sigga.tonar.data.Result;


public interface MidiConcertsCallback {

    void serviceSuccess(Result result);
    void serviceFail(Exception ex);

}
