package com.example.sigga.tonar.data;


public class Results {
    private String eventDateName;
    private String name;
    private String dateOfShow;
    private String userGroupName;
    private String eventHallName;
    private String imageSource;
    private int pos;


    public Results(String eventDateName,    String name,
                   String dateOfShow,       String userGroupName,
                   String eventHallName,      String imageSource, int pos) {
        this.eventDateName = eventDateName;
        this.name = name;
        this.dateOfShow = dateOfShow;
        this.userGroupName = userGroupName;
        this.eventHallName = eventHallName;
        this.imageSource = imageSource;
        this.pos = pos;

    }

    public int getPos(){ return pos; }

    public void setPos(int pos){
        this.pos = pos;
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
