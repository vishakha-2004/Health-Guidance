package com.example.healthguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;

public class predictOrDiagnosis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict_or_diagnosis);

        Button diagnosis = findViewById(R.id.button2);
        diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(predictOrDiagnosis.this, loginActivity.class));
            }
        });

        Button predictbutton = findViewById(R.id.predict);
        predictbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(predictOrDiagnosis.this, MainActivity.class ));
            }
        });
    }
}