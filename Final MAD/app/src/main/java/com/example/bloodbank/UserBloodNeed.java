package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

public class UserBloodNeed extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] bgroup = {"A+", "A-", "AB+", "AB-", "O+", "O-"};
    String gender;
    Button register;
    EditText pname;
    EditText pcnic;
    Spinner spbgroup;
    EditText aname;
    EditText aphone;
    EditText phone;
    EditText hname;
    RadioButton male;
    RadioButton female;
    RadioGroup rgbg;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_blood_need);

        pname = findViewById(R.id.pname);
        pcnic = findViewById(R.id.pcnic);
        aname = findViewById(R.id.aname);
        aphone = findViewById(R.id.aphone);
        phone = findViewById(R.id.phone);
        male = findViewById(R.id.male);
        male.setChecked(true);
        female = findViewById(R.id.female);
        rgbg = findViewById(R.id.rgbg);
        hname = findViewById(R.id.hname);
        register = findViewById(R.id.reg);
        db = new DatabaseHelper(this);

        rgbg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(TextUtils.isEmpty(pname.getText().toString()))
                {
                    pname.setError("Please fill this field");
                }
                else if(TextUtils.isEmpty(pcnic.getText().toString()))
                {
                    pcnic.setError("Please fill this field");
                }
                else if(TextUtils.isEmpty(hname.getText().toString()))
                {
                    hname.setError("Please fill this field");
                }
                else if(TextUtils.isEmpty(aname.getText().toString()))
                {
                    aname.setError("Please fill this field");
                }
                else if(TextUtils.isEmpty(aphone.getText().toString()))
                {
                    aphone.setError("Please fill this field");
                }
                else if(TextUtils.isEmpty(phone.getText().toString()))
                {
                    phone.setError("Please fill this field");
                }
                else {
                    boolean inserted = db.insertbloodneed(pname.getText().toString(), pcnic.getText().toString(), hname.getText().toString(), aname.getText().toString(), aphone.getText().toString(), phone.getText().toString(), gender, spbgroup.getSelectedItem().toString());
                    if (inserted = true) {
                        Toast.makeText(UserBloodNeed.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(UserBloodNeed.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
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