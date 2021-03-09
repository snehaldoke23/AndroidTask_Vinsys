package com.example.androidtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.androidtest.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button register, show;
    EditText fname, lname, email, phoneno;
    // The validator for the email input field.
    private EmailValidator mEmailValidator;
    DatabaseHelper db;
    ArrayList<DataEntry> dataEntryArrayList;
    private View view;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EmailValidator mEmailValidator;

        register = findViewById(R.id.buttonRegister);
        show = findViewById(R.id.buttonShowAll);
        fname = findViewById(R.id.editTextTextPersonName2);
        lname = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress);
        phoneno = findViewById(R.id.editTextPhone);

        // Setup field validators.
        mEmailValidator = new EmailValidator();
        email.addTextChangedListener(mEmailValidator);


        db=new DatabaseHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataEntry dataEntry = new DataEntry();
                dataEntry.setFname(fname.getText().toString());
                dataEntry.setLname(lname.getText().toString());
                dataEntry.setEmail(email.getText().toString());
                dataEntry.setPhoneno(phoneno.getText().toString());

                if (!mEmailValidator.isValid()) {
                    email.setError("Invalid email");
                    Log.w(TAG, "Not saving personal information: Invalid email");
                    return;
                }

                Long result = db.insert(dataEntry);
                Log.i("result ",String.valueOf(result));

                Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_LONG).show();
                String Sfname = fname.getText().toString().trim();
                String Slname = lname.getText().toString().trim();
                String Semail = email.getText().toString().trim();
                String Sphno = phoneno.getText().toString().trim();
                SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("first name",Sfname);
                editor.putString("last name",Slname);
                editor.putString("email",Semail);
                editor.putString("phone no",Sphno);
                editor.apply();



                Intent registerIntent = new Intent(MainActivity.this,Screen2.class);
                startActivity(registerIntent);

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserList.class);
                startActivity(intent);

                dataEntryArrayList=db.getAllData();
                Toast.makeText(getApplicationContext(),dataEntryArrayList.get(0).getFname(),Toast.LENGTH_SHORT).show();
                Log.i("Visitors :",dataEntryArrayList.get(0).fname);
            }

        });
    }

        @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("Do You Want To Exit App");
        builder.setCancelable(false);

        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create();
        builder.show();


            }

}