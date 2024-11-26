
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ViewCartActivity extends AppCompatActivity {

    private String[][] packages = {};

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
      //2d blank string
    HashMap<String, String> item;
    SimpleAdapter simpleAdapter;
    ArrayList list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        Button back = findViewById(R.id.exit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewCartActivity.this, LabTestActivity.class));
            }
        });


        //SharedPreferences is an class ..............getShredPreferences() is an method that returns object of SharedPreferences with specific preference file name
        //Context private ensures that shared_pre prefernce fie is private to application ie can be only accessed by the application

        SharedPreferences sharedPreferences = getSharedPreferences("shared_pre", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("username" ,"").toString();

        //getString() fetches the value associated with key"username".....if key "username " doesnt exist it returns empty string

        Database db = new Database(getApplicationContext() , "healthrecommendation",null,1);
        float totalCost =0;
        ArrayList Data = db.CartData(userName , "lab");
        //Toast.makeText(getApplicationContext(), ""+Data, Toast.LENGTH_LONG).show();

        packages = new String[Data.size()][];//first index is total length of array
        for(int i=0; i<packages.length;i++){
            packages[i] = new String[5];   //as in other activities like labtest we define 5 columns
        }

        for(int i=0;i<Data.size();i++){          //pass data to packages
            String DataArr = Data.get(i).toString();
            String[] strData = DataArr.split(java.util.regex.Pattern.quote("$")); //split the string with $
            packages[i][0]=strData[0];
            packages[i][4]="Cost:"+strData[1]+"/-";
            totalCost = totalCost+Float.parseFloat(strData[1]);
        }

        TextView tcost = findViewById(R.id.totalcost);
       tcost.setText("Total Cost:"+totalCost);

        list = new ArrayList();        //to store data in that list
        for(int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line 1",packages[i][0]);
            item.put("line 2",packages[i][1]);
            item.put("line 3",packages[i][2]);
            item.put("line 4",packages[i][3]);
            item.put("line 5",packages[i][4]);
            list.add(item);
        }
        //store data in line 1, 2,3 .....and then map this data into multiline layout using adapter below
        // and then display in listview
        simpleAdapter = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line 1","line 2","line 3","line 4","line 5"},
                new int[] {R.id.line1,R.id.line2,R.id.line3a,R.id.line3b,R.id.line3c}
        );
        ListView lst = findViewById(R.id.list_item);
        lst.setAdapter(simpleAdapter);


        Button datebtn = findViewById(R.id.buttonDate);
        Button timebtn = findViewById(R.id.buttonTime);

        Button checkout = findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewCartActivity.this, LabTestBooking.class);
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
//        Button timebtn = findViewById(R.id.buttonTime);
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
//
