package com.example.y2s2mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.y2s2mad.All_Tutorials.T4_ImageHandling;
import com.example.y2s2mad.All_Tutorials.T6_Firebase;

public class Tutorials extends AppCompatActivity {

    Button btn_tute4, btn_tute6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);

        btn_tute4 = findViewById(R.id.btn_tute4);
        btn_tute6 = findViewById(R.id.btn_tute6);

        btn_tute4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tutorials.this, T4_ImageHandling.class);
                startActivity(intent);
            }
        });

        btn_tute6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tutorials.this, T6_Firebase.class);
                startActivity(intent);
            }
        });
    }
}