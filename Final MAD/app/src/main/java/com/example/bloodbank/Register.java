package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] bgroup = {"A+", "A-", "AB+", "AB-", "O+", "O-"};
    String id;
    String gender;
    EditText name;
    EditText username;
    EditText password;
    EditText email;
    Spinner spbgroup;
    RadioGroup rdbg;
    RadioButton male;
    RadioButton female;
    Button register;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        db = new DatabaseHelper(this);
        register = findViewById(R.id.register);
        rdbg = findViewById(R.id.rgbg);
        male = findViewById(R.id.male);
        male.setChecked(true);
        female = findViewById(R.id.female);
        AddData();

        rdbg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male)
                {
                    gender=male.getText().toString();

                }
                else if(checkedId == R.id.female)
                {
                    gender=female.getText().toString();
                }
            }
        });


        spbgroup = findViewById(R.id.bgroup);
        ArrayAdapter<String> adbg = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bgroup);
        adbg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spbgroup.setAdapter(adbg);
        spbgroup.setOnItemSelectedListener(this);
    }

    public void AddData() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    name.setError("Please fill this field");
                } else if (TextUtils.isEmpty(username.getText().toString())) {
                    username.setError("Please fill this field");
                } else if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Please fill this field");
                } else if (TextUtils.isEmpty(email.getText().toString())) {
                    email.setError("Please fill this field");
                } else {
                    boolean inserted = db.insertdata(name.getText().toString(), username.getText().toString(), email.getText().toString(), password.getText().toString(), gender, spbgroup.getSelectedItem().toString());
                    if (inserted = true) {
                        Toast.makeText(Register.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Register.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


