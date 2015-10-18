package com.example.sigga.tonar.data;

import org.json.JSONObject;

/**
 * Created by sigga on 8.10.2015.
 */
public class EventName implements JSONPopulator {
    private String name;

    public String getEventName() {
        return name;
    }

    @Override
    public void populate(JSONObject data) {

        name = data.optString("eventDateName");

    }
}
