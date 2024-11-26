package com.example.healthguide;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class MedicineCartActivity extends AppCompatActivity {

    private String[][] medicines ={};
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_cart);

        HashMap<String,String> item;
        ArrayList list;
        SimpleAdapter simpleAdapter;


        Database db = new Database(getApplicationContext(),"healthrecommendation",null,1);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_pre", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username"," ").toString();

        float totalCost =0;
        ArrayList Data = db.CartData(username,"medicine");
        Toast.makeText(getApplicationContext()," "+Data,Toast.LENGTH_SHORT).show();

        medicines = new String[Data.size()][];//first index is total length of array
        for(int i=0; i<medicines.length;i++){
            medicines[i] = new String[5];   //as in other activities like buymedicine we define 5 columns
        }

        for(int i=0;i<Data.size();i++){          //pass data to packages
            String DataArr = Data.get(i).toString();
            String[] strData = DataArr.split(java.util.regex.Pattern.quote("$")); //split the string with $
            medicines[i][0]=strData[0];
            medicines[i][4]="Cost:"+strData[1]+"/-";
            totalCost = totalCost+Float.parseFloat(strData[1]);
        }

        TextView tcost = findViewById(R.id.totalcost);
        tcost.setText("Total Cost:"+totalCost);


        list = new ArrayList();        //to store data in that list
        for(int i=0;i<medicines.length;i++){
            item = new HashMap<String,String>();
            item.put("line 1",medicines[i][0]);
            item.put("line 2",medicines[i][1]);
            item.put("line 3",medicines[i][2]);
            item.put("line 4",medicines[i][3]);
            item.put("line 5",medicines[i][4]);
            list.add(item);
        }

        simpleAdapter = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line 1","line 2","line 3","line 4","line 5"},
                new int[] {R.id.line1,R.id.line2,R.id.line3a,R.id.line3b,R.id.line3c});
        ListView lst = findViewById(R.id.list_item);
        lst.setAdapter(simpleAdapter);



        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedicineCartActivity.this , BuyMedicineActivity.class));
            }
        });


        Button datebtn = findViewById(R.id.buttonDate);
        Button timebtn = findViewById(R.id.buttonTime);

        Button checkout = findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MedicineCartActivity.this, BookMedicinesActivity.class);
                i.putExtra("price",tcost.getText());
                i.putExtra("date",datebtn.getText());
                i.putExtra("time",timebtn.getText());
                startActivity(i);
            }
            });


        initDatePicker();
        //Button datebtn = findViewById(R.id.buttonDate);
        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        initTimePicker();
       //Button timebtn = findViewById(R.id.buttonTime);
        timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
    }


    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Button datebtn = findViewById(R.id.buttonDate);
                datebtn.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);     //these will give current values of year date and month from calendar object
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int year = 0, month =0, day=0;        //this will initialize values with 0

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() + 86400000);
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Button timebtn = findViewById(R.id.buttonTime);
                timebtn.setText(hourOfDay + ":" + minute);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);

        // int style = AlertDialog.THEME_HOLO_DARK;
        // timePickerDialog = new TimePickerDialog(this,style,  timeSetListener, hours, min, true);
        timePickerDialog = new TimePickerDialog(this,  timeSetListener, hours, min, true);
    }

}
