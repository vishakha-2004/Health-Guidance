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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class appointmentActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;  //private modifiers are allowed here only
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        TextView title = findViewById(R.id.textBookApt);
        Intent i = getIntent();
        String subtitle = i.getStringExtra("text1");
        title.setText(subtitle);

        EditText name = findViewById(R.id.name);
        String fullname = i.getStringExtra("text2");
        name.setText(fullname);

        EditText contact = findViewById(R.id.phoneno);
        String cont = i.getStringExtra("text7");
        contact.setText(cont);

        EditText address = findViewById(R.id.address);
        String add = i.getStringExtra("text4");
        address.setText(add);

        EditText fee = findViewById(R.id.fees);
        String fees = i.getStringExtra("text6");
        fee.setText("consult fees:" + fees + "/-");

        name.setKeyListener(null);    //Makes the text read-only
        contact.setKeyListener(null);
        address.setKeyListener(null);
        fee.setKeyListener(null);

        Button exit = findViewById(R.id.exitbutton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(appointmentActivity.this,DoctorDetailActivity.class));
            }
        });



        //***********DATE PICKER*******************
        initDatePicker();
        Button datebtn = findViewById(R.id.buttonDate);
        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();     //just show datePickerDialog
            }
        });
        //****************TIME PICKER*********************
        initTimePicker();
        Button timebtn = findViewById(R.id.buttonTime);
        timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerDialog.show();
            }
        });

        Button book = findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(),"healthrecommendation",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_pre", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","");

                if(db.checkAppointment(username,subtitle+ "=>"+fullname,add,cont,
                        datebtn.getText().toString(),timebtn.getText().toString())==1){
                    Toast.makeText(getApplicationContext(),"Appointment already booked", Toast.LENGTH_SHORT).show();
                }else{
                    db.placeorder(username,subtitle+"=>"+fullname,add,cont,0,datebtn.getText().toString(),timebtn.getText().toString(),
                            Float.parseFloat(fees),"appointment");
                    Toast.makeText(getApplicationContext(),"Appointment booked",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(appointmentActivity.this,HomeActivity.class));
                }
            }
        });

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


}