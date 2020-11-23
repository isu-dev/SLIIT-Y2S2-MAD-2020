package com.example.y2s2mad.All_Tutorials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.y2s2mad.R;
import com.example.y2s2mad.All_Tutorials.T5_Database.DBHandler;

import java.util.List;

public class T5_DataHandling extends AppCompatActivity {

    EditText t5_et_username, t5_et_password;
    Button t5_btn_selectAll, t5_btn_add, t5_btn_signIn, t5_btn_delete,  t5_btn_update;

    DBHandler dbHandler =  new DBHandler(this);
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t5__data_handling);

        t5_et_username = findViewById(R.id.t5_et_username);
        t5_et_password = findViewById(R.id.t5_et_password);

        t5_btn_selectAll = findViewById(R.id.t5_btn_selectAll);
        t5_btn_add = findViewById(R.id.t5_btn_add);
        t5_btn_signIn = findViewById(R.id.t5_btn_signIn);
        t5_btn_delete = findViewById(R.id.t5_btn_delete);
        t5_btn_update = findViewById(R.id.t5_btn_update);

        Log.i("T5_DataHandling", "onCreate invoked()");

        t5_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();

                Log.i("Add Entry", "In main function");

                if(username.length() == 0)
                    Toast.makeText(T5_DataHandling.this, "Enter username", Toast.LENGTH_SHORT).show();
                else if(password.length() == 0)
                    Toast.makeText(T5_DataHandling.this, "Enter password", Toast.LENGTH_SHORT).show();
                else {
                    Log.i("Add Entry", "calling function");
                    dbHandler.addInfo(username, password);
                }
            }
        });

        t5_btn_selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List userNames = dbHandler.readAllInfo();

                for(int i = 0 ; i < userNames.size() ; ++i)
                    Log.i("T5_DataHandling", userNames.get(i).toString());
            }
        });

        t5_btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();
                boolean found = false;

                if(username.length() == 0)
                    Toast.makeText(T5_DataHandling.this, "Enter username", Toast.LENGTH_SHORT).show();
                else if(password.length() == 0)
                    Toast.makeText(T5_DataHandling.this, "Enter password", Toast.LENGTH_SHORT).show();
                else {
                    found = dbHandler.readInfo(username, password);
                }

                if(found)
                    Toast.makeText(T5_DataHandling.this, "User Exists", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(T5_DataHandling.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        t5_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();

                if(username.length() == 0)
                    Toast.makeText(T5_DataHandling.this, "Enter username", Toast.LENGTH_SHORT).show();
                else
                    dbHandler.deleteInfo(t5_et_username.getText().toString());

                Toast.makeText(T5_DataHandling.this, "User deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        t5_btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();

                if(username.length() == 0)
                    Toast.makeText(T5_DataHandling.this, "Enter username", Toast.LENGTH_SHORT).show();
                else if(password.length() == 0)
                    Toast.makeText(T5_DataHandling.this, "Enter password", Toast.LENGTH_SHORT).show();
                else
                    dbHandler.updateInfo(t5_et_username.getText().toString(), t5_et_password.getText().toString());
            }
        });

    }

    public void getInput() {
        username  = t5_et_username.getText().toString();
        password = t5_et_password.getText().toString();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("T5_DataHandling", "onStart() invoked");
    }
}