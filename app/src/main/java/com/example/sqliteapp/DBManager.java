package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//  https://developer.android.com/training/data-storage/sqlite

public class DBManager {
    private DatabaseHelper databaseHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    //    Before performing any database operations like insert, update, delete records in a table, first open the database connection
    public DBManager open() {
        databaseHelper = new DatabaseHelper(context);
//        Create and/or open a database that will be used for reading and writing.
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    //    Close any open database object.
    public void close() {
        databaseHelper.close();
    }

    /* Inserting new Record into Android SQLite database table
        Returning the primary key value of the new row
        or it will return -1 if there was an error inserting the data.
        This can happen if you have a conflict with pre-existing data in the database.
     */
    public long insert(String index, String name, String surname) {
//        Content Values creates an empty set of values using the given initial size
//        This class is used to store a set of values
        ContentValues contentValue = new ContentValues();

        contentValue.put(DatabaseHelper.INDEX, index);
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.SURNAME, surname);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
        return newRowId;
    }

    //    Read information from a database
    public Cursor fetch() {
//     Define a projection that specifies which columns from the database
//     you will actually use after this query.
        String[] projection = {
                DatabaseHelper._ID,
                DatabaseHelper.INDEX,
                DatabaseHelper.NAME,
                DatabaseHelper.SURNAME
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                databaseHelper.INDEX + " DESC";

        Cursor cursor = database.query(
                DatabaseHelper.TABLE_NAME,      // The table to query
                projection,                     // The array of columns to return (pass null to get all)
                null,                   // The columns for the WHERE clause
                null,               // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder                    // The sort order
        );

//        Once the query is fetched a call to cursor.moveToFirst() is made.
//        Calling moveToFirst() it moves the cursor to the first result (when the set is not empty)
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //    Deleting a Record in Android SQLite database table
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

    //    Updating Record in Android SQLite database table
    public int update(long _id, String index, String name, String surname) {
//        Content Values creates an empty set of values using the given initial size
//        This class is used to store a set of values
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.INDEX, index);
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.SURNAME, surname);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }
}
