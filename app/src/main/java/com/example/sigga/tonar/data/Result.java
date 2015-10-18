package com.example.sigga.tonar.data;

import org.json.JSONObject;

/**
 * Created by sigga on 8.10.2015.
 */
public class Result implements JSONPopulator {

    private EventName event;
    private Name name;

    public EventName getEvent() {
        return event;
    }

    public Name getName() {

        return name;
    }

    @Override
    public void populate(JSONObject data) {

        name = new Name();
        name.populate(data.optJSONObject("name"));

        event = new EventName();
        event.populate((data.optJSONObject("eventDateName")));


    }

}
