package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class Screen2 extends AppCompatActivity {
    Button home;
    TextView fn,ln,em,pn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        fn=findViewById(R.id.Sfname);
        ln=findViewById(R.id.Slname);
        em=findViewById(R.id.Semail);
        pn=findViewById(R.id.Sphoneno);

        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String fname = sharedPreferences.getString("first name","");
        fn.setText(fname);
        String lname = sharedPreferences.getString("last name","");
        ln.setText(lname);
        String email = sharedPreferences.getString("email","");
        em.setText(email);
        String phoneno = sharedPreferences.getString("phone no","");
        pn.setText(phoneno);


        home= findViewById(R.id.button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(Screen2.this,MainActivity.class);
                startActivity(homeIntent);
            }
        });

    }

}