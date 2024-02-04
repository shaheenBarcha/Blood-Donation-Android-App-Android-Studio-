package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btnlogin);
        db = new DatabaseHelper(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                Cursor rs = db.validate(username.getText().toString(), password.getText().toString());

                if (TextUtils.isEmpty(username.getText().toString())) {
                    username.setError("Please fill out this field");
                }
                else if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Please fill out this field");
                }
                else {
                    if (rs.moveToFirst()) {
                        Intent intent = new Intent(Login.this, UserLogin.class);
                        startActivity(intent);

                    }
                    else if (user.equals("admin") && pass.equals("123")) {
                        Intent intent = new Intent(Login.this, BloodBankLogin.class);
                        startActivity(intent);
                    }
                    else
                        {
                        Toast.makeText(Login.this, "Incorrect Username/Password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}