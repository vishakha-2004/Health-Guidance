package com.example.healthguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPrefer = getSharedPreferences("shared_pre" , Context.MODE_PRIVATE);
        String username = sharedPrefer.getString("username","").toString();
        Toast.makeText(getApplicationContext(), "Welcome"+username, Toast.LENGTH_SHORT).show();

        CardView logout = findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPrefer.edit();
                editor.clear();            //clear everything stored in local memory or shared memory
                editor.apply();            //apply changes
                startActivity(new Intent(HomeActivity.this, loginActivity.class));
            }
        });


        CardView findDoc = findViewById(R.id.Consult);
        findDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this , FindDocActivity.class));
            }
        });


        CardView lab = findViewById(R.id.LabTest);
        lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this , LabTestActivity.class));
            }
        });


        CardView orderDetails = findViewById(R.id.orderDetails);
        orderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this , orderDetailsActivity.class));
            }
        });


        CardView buyMedicines = findViewById(R.id.Medicine);
        buyMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this , BuyMedicineActivity.class));
            }
        });
//
//
        CardView articles=findViewById(R.id.Articles);
        articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this , HealthArticlesActivity.class));
            }
        });

        CardView books=findViewById(R.id.Bookrecommendation);
        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this , MedicalBooksActivity.class));
            }
        });

        CardView predict = findViewById(R.id.predict);
        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }
        });

    }
}