package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private EditText index;
    private EditText name;
    private EditText surname;
    private Button back;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initialization();
        setBack();
        setCreate();
    }

    private void initialization() {
        index = findViewById(R.id.index);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        back = findViewById(R.id.back);
        create = findViewById(R.id.update);
    }

    private void setBack() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setCreate() {
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);

                DBManager database = new DBManager(getApplicationContext());
                database.open();
                database.insert(index.getText().toString(), name.getText().toString(), surname.getText().toString());

                database.close();
                startActivity(intent);
            }
        });
    }
}
