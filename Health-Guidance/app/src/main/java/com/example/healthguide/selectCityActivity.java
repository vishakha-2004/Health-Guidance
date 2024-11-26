package com.example.healthguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class selectCityActivity extends AppCompatActivity {

    private String selectedCity;
    private String doctorCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        Spinner spinnerCities = findViewById(R.id.spinner);//Spinner widget to display list of cities
        selectedCity = null;

        String[] cities = {"Mumbai", "Delhi", "Bengaluru", "Hyderabad", "Ahmedabad",
                "Chennai", "Kolkata", "Surat", "Pune", "Jaipur",
                "Lucknow", "Kanpur", "Nagpur", "Indore", "Bhopal",
                "Thane", "Pimpri-Chinchwad", "Vadodara", "Ghaziabad", "Ludhiana",
                "Coimbatore", "Agra", "Madurai", "Jodhpur", "Raipur",
                "Bhubaneswar", "Nashik", "Faridabad", "Meerut", "Jabalpur"
        };

        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selectCityActivity.this, FindDocActivity.class));
            }
        });

        // Get the doctor category from the intent
        //doctorCategory = getIntent().getStringExtra("doctor_category");
        Intent i = getIntent();
        doctorCategory=i.getStringExtra("doctor_category"); //we are not showing the heading here so it is not mapped with id here

        // Create an ArrayAdapter using the city array and a default Spinner layout
       // ArrayAdapter<String>--way to bind a list of data in this case, array of Strings ,to a Spinner widget
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, cities);

        //android.R.layout.simple_spinner_item-built-in basic layout that displays the text of each item in the Spinner

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Sets layout for dropdown menu
        spinnerCities.setAdapter(adapter); //binds the adapter to the Spinner to populate it with city names.

        // Handle item selection
        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Update selectedCity with the selected city
                selectedCity = (String) parent.getItemAtPosition(position);
                Toast.makeText(selectCityActivity.this, "Selected city: " + selectedCity, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        Button proceed = findViewById(R.id.proceed);  // Ensure button ID matches XML
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCity != null && doctorCategory != null) {
                    Intent i = new Intent(selectCityActivity.this, DoctorDetailActivity.class);
                    i.putExtra("doctor_category", doctorCategory);
                    i.putExtra("city", selectedCity);
                    startActivity(i);
                } else {
                    Toast.makeText(selectCityActivity.this, "Please select a city", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}