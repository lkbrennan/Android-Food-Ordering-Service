package com.example.lauren.assignment;

/*
Login Page
Page for users to login
Allows users to input email and password
Either gives back error message if account doesn't exist/ password or email are incorrect
or
logs user into their account
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.lauren.assignment.MainActivity.logged;

public class Login extends AppCompatActivity{
    Button sign_up_button;
    Button log_in_button;
    EditText email;
    EditText password;
    restaurantdbhelper rest;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //finding widgets
        sign_up_button = findViewById(R.id.sign_up_button);
        log_in_button = findViewById(R.id.log_in_button);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        //instantiating new database helper
        rest = new restaurantdbhelper(this);

        //brings user to sign up page
        sign_up_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(Login.this, Signup.class);
                //myIntent.putExtra("key", value); //Optional parameters
                Login.this.startActivity(myIntent);
            }
        });

        //logs in user
        //checks if their credentials are correct
        log_in_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //get text from edittext fields
                String useremail = email.getText().toString();
                String userpass = password.getText().toString();

                //if edittext fields are empty, will display error message
                if((useremail.matches(""))||(userpass.matches(""))){
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(Login.this);
                    mBuilder.setTitle(R.string.loginemptytitle);
                    mBuilder.setMessage(R.string.loginemptymsg);
                    mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = mBuilder.create();
                    alertDialog.show();
                }
                else {
                    boolean result = rest.selectUserLogin(useremail, userpass);

                    //if credentials correct, will log in
                    if (result == true) {
                        logged = true;
                        rest.selectUserAccount();
                        Intent myIntent = new Intent(Login.this, Account.class);
                        Login.this.startActivity(myIntent);
                    }
                    //else will show error message that account doesnt exist
                    if (result == false) {
                        logged = false;
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Login.this);
                        mBuilder.setTitle(R.string.loginfailtitle);
                        mBuilder.setMessage(R.string.loginfailmsg);
                        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog alertDialog = mBuilder.create();
                        alertDialog.show();
                    }
                }
            }
        });
    }
}

