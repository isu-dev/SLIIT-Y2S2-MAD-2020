package com.example.y2s2mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.y2s2mad.Lab_Sheets.L4_Fragments;

public class LabSheets extends AppCompatActivity {

    Button btn_lab4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_sheets);

        btn_lab4 = findViewById(R.id.btn_lab4);

        btn_lab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LabSheets.this, L4_Fragments.class);
                startActivity(intent);
            }
        });
    }
}