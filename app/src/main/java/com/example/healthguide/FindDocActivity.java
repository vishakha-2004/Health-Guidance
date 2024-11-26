package com.example.healthguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

public class FindDocActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doc);


        CardView home = findViewById(R.id.home);       //cardview object mapped with xml
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDocActivity.this , HomeActivity.class));
            }
        });


        CardView physician = findViewById(R.id.Physician);
        physician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocActivity.this , selectCityActivity.class);
                it.putExtra("doctor_category","Physician");  //This will pass a msg from one activity to another activity
                startActivity(it);
            }
        });


        CardView dietitian = findViewById(R.id.Dietitian);
        dietitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocActivity.this , selectCityActivity.class);
                it.putExtra("doctor_category","Dietitian");  //This will pass a msg from one activity to another activity
                startActivity(it);
            }
        });


        CardView cardio = findViewById(R.id.cardiologist);
        cardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocActivity.this , selectCityActivity.class);
                it.putExtra("doctor_category","Cardiologist");  //This will pass a msg from one activity to another activity
                startActivity(it);
            }
        });


        CardView dentist = findViewById(R.id.Dentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocActivity.this , selectCityActivity.class);
                it.putExtra("doctor_category","Dentist");  //This will pass a msg from one activity to another activity
                startActivity(it);
            }
        });


        CardView surgeon = findViewById(R.id.Surgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocActivity.this , selectCityActivity.class);
                it.putExtra("doctor_category","Surgeon");  //This will pass a msg from one activity to another activity
                startActivity(it);
            }
        });


        CardView neurologist = findViewById(R.id.neurologist);
        neurologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocActivity.this , selectCityActivity.class);
                it.putExtra("doctor_category","Neurologist");  //This will pass a msg from one activity to another activity
                startActivity(it);
            }
        });

        CardView allergist = findViewById(R.id.allergist);
        allergist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocActivity.this , selectCityActivity.class);
                it.putExtra("doctor_category","Allergist");  //This will pass a msg from one activity to another activity
                startActivity(it);
            }
        });

        CardView hairdoc = findViewById(R.id.hairdoc);
        hairdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocActivity.this , selectCityActivity.class);
                it.putExtra("doctor_category","Hair Specialist");  //This will pass a msg from one activity to another activity
                startActivity(it);
            }
        });

        CardView dermatologist= findViewById(R.id.skindoc);
        dermatologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocActivity.this , selectCityActivity.class);
                it.putExtra("doctor_category","Dermatologist");  //This will pass a msg from one activity to another activity
                startActivity(it);
            }
        });

        CardView gynac = findViewById(R.id.gynac);
        gynac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocActivity.this , selectCityActivity.class);
                it.putExtra("doctor_category","Gynaecologist");  //This will pass a msg from one activity to another activity
                startActivity(it);
            }
        });


        CardView eye = findViewById(R.id.eye);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocActivity.this , selectCityActivity.class);
                it.putExtra("doctor_category","Eye Specialist");  //This will pass a msg from one activity to another activity
                startActivity(it);
            }
        });

        DrawerLayout drawerLayout= findViewById(R.id.drawerlayout);
        ImageButton imageButton = findViewById(R.id.drawerbtn);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDocActivity.this , drawerActivity.class));
                drawerLayout.open();
            }
        });

    }
}