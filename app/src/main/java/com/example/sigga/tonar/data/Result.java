package com.example.sigga.tonar.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sigga on 8.10.2015.
 */
public class Result implements JSONPopulator {

    private EventDateName eventDateName;
    private Name name;
    private DateOfShow dateOfShow;
    private EventHallName eventHallName;
    private ImageSource imageSource;
    private UserGroupName userGroupName;

    public Result(EventDateName eventDateName, Name name,
                  DateOfShow dateOfShow, EventHallName eventHallName,
                  ImageSource imageSource, UserGroupName userGroupName) {
        this.eventDateName = eventDateName;
        this.name = name;
        this.dateOfShow = dateOfShow;
        this.eventHallName = eventHallName;
        this.imageSource = imageSource;
        this.userGroupName = userGroupName;
    }

    public EventDateName getEventDateName() {
        return eventDateName;
    }

    public Name getName() {
        return name;
    }

    public DateOfShow getDateOfShow() {
        return dateOfShow;
    }

    public EventHallName getEventHallName() {
        return eventHallName;
    }

    public ImageSource getImageSource() {
        return imageSource;
    }

    public UserGroupName getUserGroupName() {
        return userGroupName;
    }

    @Override
    public void populate(JSONObject data) {

        name = new Name();
        name.populate(data.optJSONObject("name"));

        eventDateName = new EventDateName();
        eventDateName.populate((data.optJSONObject("eventDateName")));

        dateOfShow = new DateOfShow();
        dateOfShow.populate(data.optJSONObject("dateOfShow"));

        eventHallName = new EventHallName();
        eventHallName.populate(data.optJSONObject("eventHallName"));

        imageSource = new ImageSource();
        imageSource.populate(data.optJSONObject("imageSource"));

        userGroupName = new UserGroupName();
        userGroupName.populate(data.optJSONObject("userGroupName"));


    }

}
