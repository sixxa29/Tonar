package com.example.sigga.tonar.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sigga on 27.10.2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "CONCERTS_DB";
    public static final int DB_VERSION = 2;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ConcertDB.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ConcertDB.dropTable(db);
        onCreate( db );
    }
}
