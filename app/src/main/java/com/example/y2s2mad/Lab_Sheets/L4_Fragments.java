package com.example.y2s2mad.Lab_Sheets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.y2s2mad.R;

public class L4_Fragments extends AppCompatActivity {

    Button l4_btn_fragment1, l4_btn_fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l4__fragments);

        l4_btn_fragment1 = findViewById(R.id.l4_btn_fragment1);
        l4_btn_fragment2 = findViewById(R.id.l4_btn_fragment2);


        l4_btn_fragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment;
                fragment = new L4_Fragment1();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.L4_fgmntDefault, fragment);
                ft.commit();
            }
        });

        l4_btn_fragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment;
                fragment = new L4_Fragment2();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.L4_fgmntDefault, fragment);
                ft.commit();
            }
        });
    }

    public void changeFragment(View view) {
        Fragment fragment;

        if(view == findViewById(R.id.l4_btn_fragment1)) {
            fragment = new L4_Fragment1();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.L4_fgmntDefault, fragment);
            ft.commit();
        }

        if(view == findViewById(R.id.l4_btn_fragment2)) {
            fragment = new L4_Fragment2();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.L4_fgmntDefault, fragment);
            ft.commit();
        }
    }
}