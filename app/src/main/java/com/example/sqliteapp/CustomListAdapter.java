package com.example.sqliteapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CustomListAdapter extends CursorAdapter {
    public CustomListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.view_record, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView id = (TextView) view.findViewById(R.id.id);
        TextView index = (TextView) view.findViewById(R.id.index);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView surname = (TextView) view.findViewById(R.id.surname);

        String sID = cursor.getString(cursor.getColumnIndex(DatabaseHelper._ID));
        String sIndex = cursor.getString(cursor.getColumnIndex(DatabaseHelper.INDEX));
        String sName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME));
        String sSurname = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURNAME));

        id.setText(sID);
        index.setText(sIndex);
        name.setText(sName);
        surname.setText(sSurname);
    }
}
