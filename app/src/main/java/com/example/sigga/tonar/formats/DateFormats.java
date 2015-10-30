package com.example.sigga.tonar.formats;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormats {
    public DateFormats() {
    }
    public String getYear(String year){
        return year.split("-")[0];
    }
    public String getMonth(String month){
        return month.split("-")[1];
    }
    public String getDay(String day){
        String dayplus = day.split("-")[2];
        return day = dayplus.split("T")[0];
    }
    public String getHour(String hour){
        String hourminute = hour.split("T")[1];
        return hour = hourminute.split(":")[0];
    }
    public String getMinute(String minute){
        String hourminute = minute.split("T")[1];
        return  minute = hourminute.split(":")[1];
    }
    public String getDateFormat(String dateFormat){
        SimpleDateFormat oldformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = oldformat.parse(dateFormat);
            DateFormat formats = null;
            dateFormat = formats.getDateInstance().format(date);
        }
        catch (Exception e){
            return null;
        }
        return dateFormat;
    }
    public String getTimeFormat(String hourFormat){
        SimpleDateFormat oldformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date parseDate = oldformat.parse(hourFormat);
            hourFormat = timeFormat.format(parseDate);
        }
        catch (Exception e){
            return null;
        }
        return hourFormat;
    }
}
