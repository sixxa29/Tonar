package com.example.sigga.tonar.data;

import org.json.JSONObject;

/**
 * Created by sigga on 19.10.2015.
 */
public class EventHallName implements JSONPopulator {
    private String eventHallName;

    public String getEventHallName() {
        return eventHallName;
    }

    @Override
    public void populate(JSONObject data) {

        eventHallName = data.optString("eventHallName");

    }
}
