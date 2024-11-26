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

public class LabTestActivity extends AppCompatActivity {


    private String[][] packages = {
            {"Package 1 : Full body checkup","","","","999"},
            {"Package 2 : Blood glucose fasting","","","","1099"},
            {"Package 3 : Covid-19 antibody","","","","199"},
            {"Package 4 : Thyroid checkup","","","","499"},
            {"Package 5 : Immunity checkup","","","","699"},
    };

    private String[] packageDetails={
            "Blood glucose fasting\n"+
                   "Complete Hemogram\n"+
                   "HbA1c\n"+
                   "Iron\n"+
                   "kidney\n"+
                   "liver function",
            "Blood glucose fasting",
            "Covid-19",
            "Thyroid",
            "Complete hemogram\n"+"Thyroid\n"
    };
HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter simpleAdapter;
    //ListView lst = findViewById(R.id.list_items);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this , HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item = new HashMap<String, String>();
            item.put("line 1" , packages[i][0]);
            item.put("line 2" , packages[i][1]);
            item.put("line 3" , packages[i][2]);
            item.put("line 4" , packages[i][3]);
            item.put("line 5" ,"Cost:" +packages[i][4]+"/-");
            list.add(item);
        }

        simpleAdapter = new SimpleAdapter(this , list ,
                R.layout.multi_lines,
                new String[]{"line 1", "line 2","line 3","line 4","line 5"},   //String array with string literals ............no. of string and int literals should be same....
                new int[] {R.id.line1 , R.id.line2 , R.id.line3a , R.id.line3b,R.id.line3c});   //Integer array with int literals
        ListView lst = findViewById(R.id.list_items);
       lst.setAdapter(simpleAdapter);




        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(LabTestActivity.this , LabTestDetailsActivity.class);
                i.putExtra("text 1",packages[position][0]);
                i.putExtra("text 2",packageDetails[position]);
                i.putExtra("text 3",packages[position][4]);
            startActivity(i);
            }
        });

        Button goTocart = findViewById(R.id.toCart);
        goTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this , ViewCartActivity.class));
            }
        });

    }
}