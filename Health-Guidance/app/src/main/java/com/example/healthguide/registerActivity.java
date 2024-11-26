package com.example.healthguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {

    EditText Username, Pswd, cPass, Email;
    Button btnRegister;
    TextView tvOldUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username = findViewById(R.id.name);
        Pswd = findViewById(R.id.Password);
        cPass = findViewById(R.id.editTextConPassword);
        Email = findViewById(R.id.editTextEmail);
        btnRegister = findViewById(R.id.buttonRegister);
        tvOldUser = findViewById(R.id.textViewOldUser);

        tvOldUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), loginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName = Username.getText().toString();
                String email = Email.getText().toString();
                String confirm = cPass.getText().toString();
                String password = Pswd.getText().toString();

                Database db = new Database(getApplicationContext(),"healthrecommendation",null, 1);

                if (UserName.length() == 0 || email.length() == 0 || confirm.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirm) == 0) {
                        if(isValid(password)){
                            db.register(UserName,email,password);
                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(registerActivity.this,loginActivity.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters,having letter, digit and special symbol", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String passwd) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwd.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwd.length(); p++) {
                if (Character.isLetter(passwd.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwd.length(); r++) {
                if (Character.isDigit(passwd.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwd.length(); s++) {
                char c = passwd.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1) {
                return true;
            }else{
                return false;
            }
        }
    }
}