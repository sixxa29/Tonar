package com.example.sigga.tonar.data;

import org.json.JSONObject;

/**
 * Created by sigga on 19.10.2015.
 */
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
