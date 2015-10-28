package com.example.sigga.tonar.service;

import com.example.sigga.tonar.data.Result;
import com.example.sigga.tonar.data.Results;

import java.util.ArrayList;

/**
 * Created by sigga on 8.10.2015.
 */
public interface MidiConcertsCallback {

    void serviceSuccess(Result result);
    void serviceFail(Exception ex);

}
