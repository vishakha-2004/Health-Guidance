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

public class LabTestDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        Button back = findViewById(R.id.exit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailsActivity.this , LabTestActivity.class));
            }
        });

        TextView packageName = findViewById(R.id.testPackages);
        TextView cost = findViewById(R.id.costTotal);
        EditText details = findViewById(R.id.multiline);

        Intent i = getIntent();
        String totalCost = i.getStringExtra("text 3");
        cost.setText("Total Cost:"+totalCost+"/-");

        String packagename = i.getStringExtra("text 1");
        packageName.setText(packagename);

        String detail = i.getStringExtra("text 2");
        details.setText(detail);

        details.setKeyListener(null);

        Button addcart = findViewById(R.id.addtocart);
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_pre", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = packageName.getText().toString();
                float price = Float.parseFloat(i.getStringExtra("text 3").toString());

                Database db = new Database(getApplicationContext(),"healthrecommendation",null,1);

                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product already added", Toast.LENGTH_SHORT).show();
                }else {
                    db.addtocart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(),"Item added to cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this , LabTestActivity.class));
                }
            }
        });
    }
}