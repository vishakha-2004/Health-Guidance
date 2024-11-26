package com.example.healthguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);

        // Load the animation
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.splash);

        // Start the animation
        logo.startAnimation(fadeIn);

        // Optional: Handle actions after animation
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(splashActivity.this, predictOrDiagnosis.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
    }
}