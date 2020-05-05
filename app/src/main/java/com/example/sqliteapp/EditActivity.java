package com.example.sqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

//    private TextView textViewID;
//    private TextView textViewIndex;
//    private TextView textViewName;
//    private TextView textViewSurname;
    private TextView textViewResponseID;
    private EditText editTextIndex;
    private EditText editTextName;
    private EditText editTextSurname;
    private Button update;
    private Button cancel;
    private Button delete;

    private DBManager database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent editIntent = getIntent();
        // editText, textView, Button
        initialization();

        // create and open database
        database = new DBManager(this);
        database.open();

        // get Strings from MainActivity intent
        String id = editIntent.getStringExtra(DatabaseHelper._ID);
        String index = editIntent.getStringExtra(DatabaseHelper.INDEX);
        String name = editIntent.getStringExtra(DatabaseHelper.NAME);
        String surname = editIntent.getStringExtra(DatabaseHelper.SURNAME);
        insertIntoView(id, index, name, surname);

        // onClickListeners
        back();
        setUpdate(id);
        setDelete(id);
    }

    private void back() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpdate(final String id) {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String index = editTextIndex.getText().toString();
                String name = editTextName.getText().toString();
                String surname = editTextSurname.getText().toString();

                database.update(Long.parseLong(id), index, name, surname);
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setDelete(final String id) {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.delete(Long.parseLong(id));
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initialization() {
//        textViewID = findViewById(R.id.textView1);
//        textViewIndex = findViewById(R.id.textView2);
//        textViewName = findViewById(R.id.textView3);
//        textViewSurname = findViewById(R.id.textView4);
        textViewResponseID = findViewById(R.id.id);
        editTextIndex = findViewById(R.id.index);
        editTextName = findViewById(R.id.name);
        editTextSurname = findViewById(R.id.surname);
        update = findViewById(R.id.update);
        cancel = findViewById(R.id.back);
        delete = findViewById(R.id.delete);
    }

    private void insertIntoView(String id, String index, String name, String surname) {
        textViewResponseID.setText(id);
        editTextIndex.setText(index);
        editTextName.setText(name);
        editTextSurname.setText(surname);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // always remember to close database
        database.close();
    }
}
