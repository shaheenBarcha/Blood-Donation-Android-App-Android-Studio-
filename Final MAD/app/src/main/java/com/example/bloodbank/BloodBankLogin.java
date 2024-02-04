package com.example.bloodbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.contentcapture.DataRemovalRequest;
import android.view.textclassifier.ConversationActions;
import android.widget.Button;

public class BloodBankLogin extends AppCompatActivity {
    Button btndonor;
    Button btnneedy;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_login);

        btndonor = findViewById(R.id.viewdonors);
        btnneedy = findViewById(R.id.viewneedy);
        db = new DatabaseHelper(this);

        btndonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getDonor();
                if (res.getCount() == 0) {
                    //show message
                    showdonor("Error", "Empty");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("ID :" + res.getString(0) + "\n");
                        buffer.append("name :" + res.getString(1) + "\n");
                        buffer.append("fname :" + res.getString(2) + "\n");
                        buffer.append("age :" + res.getString(3) + "\n");
                        buffer.append("address :" + res.getString(4) + "\n\n");

                    }
                    showdonor("Blood Donors",buffer.toString());
                }
            }
        });


        btnneedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor rr = db.getData();
                if (rr.getCount() == 0) {
                    //show message
                    showneedy("Error", "Empty");
                    return;
                }
                else
                    {
                    StringBuffer buffer = new StringBuffer();
                    while (rr.moveToNext()) {
                        buffer.append("ID :" + rr.getString(0) + "\n");
                        buffer.append("Patient name :" + rr.getString(1) + "\n");
                        //buffer.append("Patient Cnic :" + rr.getString(2) + "\n");
                        //buffer.append("Hospital Name :" + rr.getString(3) + "\n");
                        //buffer.append("Attendee :" + rr.getString(4) + "\n");
                        buffer.append("Gender :" + rr.getString(5) + "\n");
                        buffer.append("Blood Group :" + rr.getString(6) + "\n\n");
                        //buffer.append("Gender :" + rr.getString(7) + "\n");
                        //buffer.append("Blood Group :" + rr.getString(8) + "\n");

                    }
                    showneedy("Blood Needy",buffer.toString());
                }
            }
        });
    }


    public void showdonor(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }


    public void showneedy(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}