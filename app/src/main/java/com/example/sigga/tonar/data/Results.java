package com.example.sigga.tonar.data;

/**
 * Created by sigga on 19.10.2015.
 */
public class Results {
    private String eventDateName;
    private String name;
    private String dateOfShow;
    private String userGroupName;
    private String eventHallName;
    private String imageSource;


    public Results(String eventDateName,    String name,
                   String dateOfShow,       String userGroupName,
                   String eventHallName,      String imageSource) {
        this.eventDateName = eventDateName;
        this.name = name;
        this.dateOfShow = dateOfShow;
        this.userGroupName = userGroupName;
        this.eventHallName = eventHallName;
        this.imageSource = imageSource;

    }

    public String getEventDateName() {
        return eventDateName;
    }

    public void setEventDateName(String eventDateName) {
        this.eventDateName = eventDateName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfShow() {
        return dateOfShow;
    }

    public void setDateOfShow(String dateOfShow) {
        this.dateOfShow = dateOfShow;
    }

    public String getEventHallName() {
        return eventHallName;
    }

    public void setEventHallName(String eventHallName) {
        this.eventHallName = eventHallName;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }
}
