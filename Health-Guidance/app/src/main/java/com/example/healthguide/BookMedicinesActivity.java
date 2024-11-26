package com.example.healthguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookMedicinesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_medicines);

        Button exit = findViewById(R.id.exitbutton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookMedicinesActivity.this , MedicineCartActivity.class));
            }
        });

        EditText name = findViewById(R.id.name);
        EditText pin = findViewById(R.id.pincode);
        EditText contact = findViewById(R.id.phoneno);
        EditText add = findViewById(R.id.address);

        Intent i = getIntent();
        String[] price = i.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = i.getStringExtra("date");
        String time = i.getStringExtra("time");

        Button book = findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(),"healthrecommendation",null,1);

                SharedPreferences sharedPreferences = getSharedPreferences("shared_pre", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","");

                db.placeorder(username , name.getText().toString(),
                        add.getText().toString(),contact.getText().toString(),
                        Integer.parseInt(pin.getText().toString()),date.toString(),time.toString(),
                        Float.parseFloat(price[1].toString()),"medicine");

                db.deleteItems(username , "medicine");
                startActivity(new Intent(BookMedicinesActivity.this , HomeActivity.class));
                Toast.makeText(BookMedicinesActivity.this, "Booking successful", Toast.LENGTH_SHORT).show();
            }
        });


    }
}