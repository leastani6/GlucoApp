package com.example.glucoapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Activity2 extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    Button login;
    EditText username,password;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_2);

        db = new DatabaseHelper(this);
       username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
       login = (Button)findViewById(R.id.button3);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if(res == true)
                {


                    Toast.makeText(Activity2.this,"Login Successful",Toast.LENGTH_SHORT).show();

                    Intent HomePage = new Intent(Activity2.this,User_home.class);

                    startActivity(HomePage);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("name",user);
                    System.out.println(user);
                  //  editor.putInt("idName", 12);
                    editor.apply();
//
//
//                    String unm=sp1.getString("Unm", null);
//                    String pass = sp1.getString("Psw", null);
//                    Ed.commit();
                }
                else
                {
                    Toast.makeText(Activity2.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        }



