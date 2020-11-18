package com.example.y2s2mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_tutes, btn_labs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_tutes = findViewById(R.id.btn_tutes);
        btn_labs = findViewById(R.id.btn_labs);

        btn_tutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent( MainActivity.this, Tutorials.class );
                startActivity(intent1);
            }
        });
    }

    public void openLabSheets(View view) {
        Intent intent2 = new Intent(MainActivity.this, LabSheets.class);
        startActivity(intent2);
    }
}