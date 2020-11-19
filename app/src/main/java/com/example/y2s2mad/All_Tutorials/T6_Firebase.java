package com.example.y2s2mad.All_Tutorials;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.y2s2mad.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class T6_Firebase extends AppCompatActivity {

    EditText t6_et_id, t6_et_name, t6_et_address, t6_et_number;
    Button t6_btn_save, t6_btn_show, t6_btn_update, t6_btn_delete;
    DatabaseReference dbRef;
    Student std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t6_firebase);

        t6_et_id = findViewById(R.id.t6_et_id);
        t6_et_name = findViewById(R.id.t6_et_name);
        t6_et_address = findViewById(R.id.t6_et_address);
        t6_et_number = findViewById(R.id.t6_et_number);

        t6_btn_save = findViewById(R.id.t6_btn_save);
        t6_btn_show =  findViewById(R.id.t6_btn_show);
        t6_btn_update = findViewById(R.id.t6_btn_update);
        t6_btn_delete = findViewById(R.id.t6_btn_delete);

        std = new Student();

        t6_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                    child("Student") = main table
                    if there are other childs they are subparts of the one before that
                 */

                dbRef = FirebaseDatabase.getInstance().getReference().child("Student");

                try {
                    if(TextUtils.isEmpty(t6_et_id.getText().toString()))
                        Toast.makeText(T6_Firebase.this, "Please enter an ID", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(t6_et_name.getText().toString()))
                        Toast.makeText(T6_Firebase.this, "Please enter a Name", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(t6_et_address.getText().toString()))
                        Toast.makeText(T6_Firebase.this, "Please enter an Address", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(t6_et_number.getText().toString()))
                        Toast.makeText(T6_Firebase.this, "Please enter a Number", Toast.LENGTH_SHORT).show();
                    else {
                        //take inputs from the user and assigning them to this instance (std)
                        std.setID(t6_et_id.getText().toString().trim());
                        std.setName(t6_et_name.getText().toString().trim());
                        std.setAddress(t6_et_address.getText().toString().trim());
                        std.setNumber(Integer.parseInt(t6_et_number.getText().toString().trim()));

                        //insert into the database
                        /*
                            setValue() is passing the object to be added to the database
                         */
                        //dbRef.push().setValue(std);
                        /*
                            setting value with specific key
                         */
                        dbRef.child("Std3").setValue(std);

                        //feedback to the user via a Toast
                        Toast.makeText(T6_Firebase.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();

                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(T6_Firebase.this, "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
            }
        });


        t6_btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Student").child("Std2");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()) {
                            t6_et_id.setText(snapshot.child("id").getValue().toString());
                            t6_et_name.setText(snapshot.child("name").getValue().toString());
                            t6_et_address.setText(snapshot.child("address").getValue().toString());
                            t6_et_number.setText(Integer.parseInt(snapshot.child("number").getValue().toString()));
                        } else
                            Toast.makeText(T6_Firebase.this, "No Source to Display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        t6_btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Student");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild("Std2")) {
                            try {
                                std.setID(t6_et_id.getText().toString().trim());
                                std.setName(t6_et_name.getText().toString().trim());
                                std.setAddress(t6_et_address.getText().toString().trim());
                                std.setNumber(Integer.parseInt(t6_et_number.getText().toString().trim()));

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("Std2");
                                dbRef.setValue(std);

                                clearControls();

                                Toast.makeText(T6_Firebase.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(T6_Firebase.this, "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(T6_Firebase.this, "No Source to Display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        t6_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Student");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild("Std3")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("Std3");
                            dbRef.removeValue();

                            clearControls();

                            Toast.makeText(T6_Firebase.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(T6_Firebase.this, "No Source to Delete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    //to clear all controls
    private void clearControls() {
        t6_et_id.setText("");
        t6_et_name.setText("");
        t6_et_address.setText("");
        t6_et_number.setText("");
    }
}