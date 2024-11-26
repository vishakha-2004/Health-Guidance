package com.example.healthguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BuyMedicinesDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicines_detail);

        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicinesDetailActivity.this,BuyMedicineActivity.class));
            }
        });

        EditText details = findViewById(R.id.multiline);
        details.setKeyListener(null);

        TextView medicineName = findViewById(R.id.buymedicines);
        TextView cost = findViewById(R.id.costTotal);

        Intent i = getIntent();
        cost.setText("Total cost:"+(i.getStringExtra("text3")));
        details.setText(i.getStringExtra("text2"));
        medicineName.setText(i.getStringExtra("text1"));



        Button addtocart = findViewById(R.id.addtocart);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                         (context for database opration , name of database,cursor factory , database version)
                //database name is used to identify the databse file ,it ensures that all activities are working or integrating with same database file
                // ....differnet database file name may cause inconsistency in application
                Database db = new Database(getApplicationContext(),"healthrecommendation",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_pre", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username"," ");
                String medicine = medicineName.getText().toString();

                float price = Float.parseFloat(i.getStringExtra("text3").toString());

                if(db.checkCart(username,medicine)==1){
                    Toast.makeText(getApplicationContext(),"Product already added", Toast.LENGTH_SHORT).show();
                }else {
                    db.addtocart(username,medicine,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Item added to cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicinesDetailActivity.this , BuyMedicineActivity.class));
                }
            }
        });
    }
}