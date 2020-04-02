package com.example.glucoapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.glucoapp.DatabaseHelper;

public class RegisterUser extends AppCompatActivity {
    DatabaseHelper db;
    Button regjistrohu;
   EditText emer;
    EditText mbiemer;
    EditText username;
    EditText password;
    EditText telefon;
   // SQLiteOpenHelper openHelper;
  // SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
       // openHelper = new DatabaseHelper(this);
        db=new DatabaseHelper(this);

        regjistrohu = (Button) findViewById(R.id.button4);
        emer = (EditText) findViewById(R.id.editText3);
        mbiemer = (EditText) findViewById(R.id.editText4);
        username = (EditText) findViewById(R.id.editText5);
        password = (EditText) findViewById(R.id.editText6);
        telefon = (EditText) findViewById(R.id.editText7);

        regjistrohu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // db = openHelper.getWritableDatabase();
                String name = emer.getText().toString().trim();
                String lastname = mbiemer.getText().toString().trim();
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String tel = telefon.getText().toString().trim();


               // Toast.makeText(RegisterUser.this, "register successful", Toast.LENGTH_SHORT).show();



            long val;
                val = db.addUser(name,lastname,user,pass,tel);
                System.out.println(val);
                if(val> 0){
                Toast.makeText(RegisterUser.this,"You have registered",Toast.LENGTH_SHORT).show();

                Intent moveToLogin = new Intent(RegisterUser.this,Activity2.class);
                startActivity(moveToLogin);
            }
                    else{
                Toast.makeText(RegisterUser.this,"Registration Error",Toast.LENGTH_SHORT).show();
            }

        }


        });

    }}