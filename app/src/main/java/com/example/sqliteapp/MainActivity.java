package com.example.sqliteapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Intent intent;
    private DBManager dbManager;
    private Button add;

    //    https://www.homeandlearn.co.uk/android/android_simple_list_items.html
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor itemValue = (Cursor) listView.getItemAtPosition(position);

//            For each row, you can read a column's value by calling one of the Cursor get methods, such as getString() or getLong().
//            getColumnIndex(String) returns the zero-based index for the given column name
            intent = new Intent(MainActivity.this, EditActivity.class);
            intent.putExtra(DatabaseHelper._ID, itemValue.getString(itemValue.getColumnIndex(DatabaseHelper._ID)));
            intent.putExtra(DatabaseHelper.INDEX, itemValue.getString(itemValue.getColumnIndex(DatabaseHelper.INDEX)));
            intent.putExtra(DatabaseHelper.NAME, itemValue.getString(itemValue.getColumnIndex(DatabaseHelper.NAME)));
            intent.putExtra(DatabaseHelper.SURNAME, itemValue.getString(itemValue.getColumnIndex(DatabaseHelper.SURNAME)));

            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.containedButton);

        // find listView
        listView = findViewById(R.id.listView);

        // instance of database
        dbManager = new DBManager(getApplicationContext());
        // make this database writable
        dbManager.open();
        // get data from database
        Cursor cursor = dbManager.fetch();

        // create custom adapter for listView
        CustomListAdapter customListAdapter = new CustomListAdapter(this, cursor, 0);

        // set adapter of listView
        listView.setAdapter(customListAdapter);
        // set onClickListener to handle updating data in database
        listView.setOnItemClickListener(listClick);

        setAdd();
    }

    private void setAdd() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // always remember to close database
        dbManager.close();
    }
}
