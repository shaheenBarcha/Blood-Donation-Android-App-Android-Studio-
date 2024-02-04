package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserDonor extends AppCompatActivity {
    EditText name;
    EditText fname;
    EditText age;
    EditText number;
    EditText address;
    Button register;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_donor);

        name = findViewById(R.id.name);
        fname = findViewById(R.id.fname);
        age = findViewById(R.id.age);
        number = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        register = findViewById(R.id.register);
        db = new DatabaseHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(TextUtils.isEmpty(name.getText().toString()))
                {
                    name.setError("Please fill this field");
                }
                else if(TextUtils.isEmpty(fname.getText().toString()))
                {
                    fname.setError("Please fill this field");
                }
                else if(TextUtils.isEmpty(age.getText().toString()))
                {
                    age.setError("Please fill this field");
                }
                else if(TextUtils.isEmpty(number.getText().toString()))
                {
                    number.setError("Please fill this field");
                }
                else if(TextUtils.isEmpty(address.getText().toString()))
                {
                    address.setError("Please fill this field");
                }
                else {
                    boolean inserted = db.insertdonor(name.getText().toString(), fname.getText().toString(), age.getText().toString(), address.getText().toString(), number.getText().toString());
                    if (inserted = true) {
                        Toast.makeText(UserDonor.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(UserDonor.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

 }
