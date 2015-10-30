package com.example.sigga.tonar.data;

import org.json.JSONObject;


public class DateOfShow implements JSONPopulator {
    private String dateOfShow;

    public String getDateOfShow() {
        return dateOfShow;
    }

    @Override
    public void populate(JSONObject data) {

        dateOfShow = data.optString("dateOfShow");

    }
}
