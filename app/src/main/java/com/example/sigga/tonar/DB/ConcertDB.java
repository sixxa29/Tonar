package com.example.sigga.tonar.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sigga on 27.10.2015.
 */
public class ConcertDB {
    public static final String TableConcerts = "concerts";
    public static final String[] TableConcertsCol = { "_id", "eventDateName", "name", "dateOfShow", "userGroupName", "eventHallName","imageSource" };

    private static final String sqlCreateTable =
            "CREATE TABLE concerts( " +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "eventDateName TEXT," +
                    "name TEXT," +
                    "dateOfShow TEXT," +
                    "userGroupName TEXT," +
                    "eventHallName TEXT," +
                    "imageSource TEXT" +
                    ");";

    private static final String sqlDropTable =
            "DROP TABLE IF EXISTS concerts;";

    public static void createTable( SQLiteDatabase db ) {
        db.execSQL( sqlCreateTable );
    }

    public static void dropTable( SQLiteDatabase db ) {
        db.execSQL( sqlDropTable );
    }

    public static long insert( SQLiteDatabase db, String eventDateName, String name,
                                String dateOfShow, String userGroupName, String eventHallName, String imageSource) {
        ContentValues cv = new ContentValues();
        cv.put( TableConcertsCol[1], eventDateName );
        cv.put( TableConcertsCol[2], name );
        cv.put( TableConcertsCol[3], dateOfShow );
        cv.put( TableConcertsCol[4], userGroupName );
        cv.put(TableConcertsCol[5], eventHallName);
        cv.put( TableConcertsCol[6], imageSource );
        return db.insert( TableConcerts, null, cv );
    }

    public static Cursor query( SQLiteDatabase db ) {
        Cursor cursor = db.query( TableConcerts, TableConcertsCol, null, null, null, null, null );
        return cursor;
    }

    public static long update( SQLiteDatabase db, int id, String eventDateName, String name,
                               String dateOfShow, String userGroupName, String eventHallName, String imageSource) {
        ContentValues cv = new ContentValues();
        cv.put( TableConcertsCol[1], eventDateName );
        cv.put( TableConcertsCol[2], name );
        cv.put( TableConcertsCol[3], dateOfShow );
        cv.put( TableConcertsCol[4], userGroupName );
        cv.put(TableConcertsCol[5], eventHallName);
        cv.put( TableConcertsCol[6], imageSource );;
        long value = db.update( TableConcerts,
                cv,
                TableConcertsCol[0] + "=" + id, null );
        return value;
    }
}
