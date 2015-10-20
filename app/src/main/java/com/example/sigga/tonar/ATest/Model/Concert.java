package com.example.sigga.tonar.ATest.Model;

/**
 * Created by sigga on 18.10.2015.
 */
public class Concert {

    String eventDateName;
    String name;
    String dateOfShow;
    String userGroupName;
    String eventHallName;
    String imageSource;

    public Concert(){

    }
    public Concert(String eventDateName, String name, String dateOfShow, String userGroupName, String eventHallName, String imageSource) {
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

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
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

    @Override
    public String toString() {
        return "Concert{" +
                "eventDateName='" + eventDateName + '\'' +
                ", name='" + name + '\'' +
                ", dateOfShow='" + dateOfShow + '\'' +
                ", userGroupName='" + userGroupName + '\'' +
                ", eventHallName='" + eventHallName + '\'' +
                ", imageSource='" + imageSource + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Concert concert = (Concert) o;

        if (!eventDateName.equals(concert.eventDateName)) return false;
        if (!name.equals(concert.name)) return false;
        if (!dateOfShow.equals(concert.dateOfShow)) return false;
        if (!userGroupName.equals(concert.userGroupName)) return false;
        if (!eventHallName.equals(concert.eventHallName)) return false;
        return imageSource.equals(concert.imageSource);

    }

    @Override
    public int hashCode() {
        int result = eventDateName.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + dateOfShow.hashCode();
        result = 31 * result + userGroupName.hashCode();
        result = 31 * result + eventHallName.hashCode();
        result = 31 * result + imageSource.hashCode();
        return result;
    }
}
