package com.example.healthguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] medicines = {
            {"Aspirin", "", "", "", "599"},
            {"Ibuprofen", "", "", "", "749"},
            {"Paracetamol", "", "", "", "499"},
            {"Amoxicillin", "", "", "", "1299"},
            {"Metformin", "", "", "", "949"},
            {"Lisinopril", "", "", "", "899"},
            {"Cetirizine", "", "", "", "649"},
            {"Simvastatin", "", "", "", "1199"},
            {"Omeprazole", "", "", "", "1049"},
            {"Losartan", "", "", "", "1399"}
    };

    private String[] medicineDetails = {
            " Used for pain relief\n "+ "Reduces inflammation",
            " Nonsteroidal anti-inflammatory drug (NSAID)\n"+" Reduces pain and fever",
            " Relieves mild to moderate pain\n"+ "Reduces fever",
            " Penicillin-type antibiotic\n"+" Treats bacterial infections",
            " Oral medication for diabetes\n"+" Helps control blood sugar levels",
            " ACE inhibitor\n"+" Treats high blood pressure and heart failure",
            " Antihistamine\n"+ "Relieves allergy symptoms",
            " Statin medication\n"+" Controls high cholesterol levels",
            " Proton pump inhibitor\n"+" Reduces stomach acid production",
            " Angiotensin II receptor blocker\n"+" Manages high blood pressure"
    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        HashMap<String,String> item;
        ArrayList list;
        SimpleAdapter simpleAdapter;
        ListView listView=findViewById(R.id.list_items);

        Button exit =findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this , HomeActivity.class));
            }
        });

        Button gotocart = findViewById(R.id.toCart);
        gotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this ,MedicineCartActivity.class ));
            }
        });

        list = new ArrayList();
        for(int i=0;i<medicines.length;i++){
            item = new HashMap<>();
            item.put("line 1",medicines[i][0]);
            item.put("line 2",medicines[i][1]);
            item.put("line 3",medicines[i][2]);
            item.put("line 4",medicines[i][3]);
            item.put("line 5","Total cost:"+medicines[i][4]+"/-");
            list.add(item);
        }

        simpleAdapter = new SimpleAdapter(this , list ,
                R.layout.multi_lines,
                new String[] {"line 1","line 2","line 3","line 4","line 5"},
                new int[] {R.id.line1 , R.id.line2,R.id.line3a,R.id.line3b,R.id.line3c});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent= new Intent(BuyMedicineActivity.this,BuyMedicinesDetailActivity.class);
                intent.putExtra("text1",medicines[i][0]);
                intent.putExtra("text2",medicineDetails[i]);
                intent.putExtra("text3",medicines[i][4]);
                startActivity(intent);
            }
        });
    }
}