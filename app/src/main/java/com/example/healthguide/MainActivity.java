package com.example.healthguide;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText symptomsInput;
    private TextView resultText;
    private Button predictButton;
//    private LinearLayout buttonContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        symptomsInput = findViewById(R.id.editTextText);
        resultText = findViewById(R.id.resultText); // Add a TextView in XML for displaying result
        predictButton = findViewById(R.id.button);
//        buttonContainer = findViewById(R.id.buttonContainer);


        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String symptoms = symptomsInput.getText().toString().trim();
                if (!symptoms.isEmpty()) {
                    predictDisease(symptoms);
                }
            }
        });
    }

    private void predictDisease(String symptoms) {
        // Split the input and create a JSON array
        String[] symptomsArray = symptoms.split(",");
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("symptoms", new JSONArray(symptomsArray));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // URL to your Flask server
        String url = "http://10.0.2.2:5000/predict";  // for running on emulator only

       //Change url to your laptop's IP for prediction on your emulator and
        // physical device both. And connect laptop and phone with same network
        //String url = "http://192.168.133.166:5000/predict";

        // Create a Volley request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String predictedDisease = response.getString("disease");
                            resultText.setText("Predicted Disease: " + predictedDisease);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultText.setText("Error: " + error.getMessage());
                    }
                }
        );

        // Add the request to the Volley queue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}