package com.example.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//  https://developer.android.com/training/data-storage/sqlite

//  Once a database is created successfully its located in data/data//databases/ accessible from Android Device Monitor.

public class DatabaseHelper extends SQLiteOpenHelper {

    //     Table Name
    public static final String TABLE_NAME = "STUDENTS";

    //     Table columns
    public static final String _ID = "_id";
    public static final String INDEX = "INDEX_NR";
    public static final String NAME = "NAME";
    public static final String SURNAME = "SURNAME";

    //     Database Information
    private static final String DB_NAME = "STUDENT.DB";

    //     database version
    private static final int DB_VERSION = 1;

    //     Creating table query
    private static final String CREATE_TABLE =
            "create table" + " " + TABLE_NAME + "("
                    + _ID + " " + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + INDEX + " " + "TEXT NOT NULL,"
                    + NAME + " " + "TEXT,"
                    + SURNAME + " " + "TEXT" + ");";

    // CREATE TABLE STUDENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT, INDEX TEXT NOT NULL, NAME TEXT, SURNAME TEXT);

    //    This takes the Context (e.g., an Activity)
    public DatabaseHelper(@Nullable Context context) {
//        When the application runs the first time – At this point, we do not yet have a database.
//        So we will have to create the tables, indexes, starter data, and so on.
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
