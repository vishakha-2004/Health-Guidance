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

public class LabTestBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_booking);

        EditText name = findViewById(R.id.name);
        EditText add = findViewById(R.id.address);
        EditText contact = findViewById(R.id.phoneno);
        EditText pin = findViewById(R.id.pincode);
        Button book = findViewById(R.id.book);
        Button exit = findViewById(R.id.exitbutton);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestBooking.this, LabTestActivity.class));
            }
        });

        Intent i = getIntent();
        String[] price = i.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        //got this price value from labtestdetails activity
        String date = i.getStringExtra("date");
        String time = i.getStringExtra("time");


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_pre", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db = new Database(getApplicationContext(),"healthrecommendation",null,1);

                db.placeorder(username , name.getText().toString(),
                        add.getText().toString(),contact.getText().toString(),
                        Integer.parseInt(pin.getText().toString()),date.toString(),time.toString(),
                        Float.parseFloat(price[1].toString()),"lab");

                db.deleteItems(username , "lab");
                Toast.makeText(getApplicationContext(),"Booking successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBooking.this , HomeActivity.class));
            }
        });

    }
}