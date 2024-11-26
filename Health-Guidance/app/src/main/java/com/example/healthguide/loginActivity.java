package com.example.healthguide;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    //    class objects
    EditText edUserName , edPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUserName = findViewById(R.id.user);  //class objects internally mapped with xml file with findviewbyid function
        edPassword = findViewById(R.id.editTextPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);

        //events
        btn.setOnClickListener(new View.OnClickListener() {       // new View.On.... is a anonymous class implements View.OnClickListener interface
            @Override                           // called when button or view is clicked
            public void onClick(View v) {     // v is view that is clicked
                String username = edUserName.getText().toString();
                String password = edPassword.getText().toString();
                //**************** **********Database part****************************
                Database db = new Database(getApplicationContext(),"healthrecommendation",null,1);
                if (username.length()==0||password.length()==0){
                    Toast.makeText(getApplicationContext(), "Fill all details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(db.login(username,password)==1){
                        Toast.makeText(loginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_pre" , Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();   //to store data , we user "Editor" object from SharedPreferences
                        editor.putString("username", username);                       //sets a string value for the specified key
                        editor.apply();         //save data with key ,value.......save changes asynchronously

                        startActivity(new Intent(loginActivity.this , HomeActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(), "Invalid username and password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Intent--way to navigate between components or activities-----explicit--used to call user defined activity or call by name
        //                                                implicit

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this ,registerActivity.class ));     //(current_activity , callable_activity) loginActivity.this or getApplicationContext() is current activity
            }
        });

    }

}