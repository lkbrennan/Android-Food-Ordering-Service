package com.example.lauren.assignment;

/*
Sign Up page
allows users to inout their details and create an account
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

public class Signup extends AppCompatActivity {

    Button sign_up_button;
    Button log_in_button;
    EditText username;
    EditText userpassword1;
    EditText userpassword2;
    EditText useremail;
    EditText userphonenumber;
    restaurantdbhelper rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sign_up_button = findViewById(R.id.sign_up_button);
        log_in_button = findViewById(R.id.log_in_button);
        username = findViewById(R.id.username);
        userpassword1 = findViewById(R.id.userpassword1);
        userpassword2 = findViewById(R.id.userpassword2);
        useremail = findViewById(R.id.useremail);
        userphonenumber = findViewById(R.id.userphonenum);

        rest = new restaurantdbhelper(this);
        log_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Signup.this, Login.class);
                //myIntent.putExtra("key", value); //Optional parameters
                Signup.this.startActivity(myIntent);
            }
        });

        //checks if user has entered in all thier details correctly before allowing them to create an account
        //if anything is wrong with their information a dialog will appear telling them what is wrong
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = userphonenumber.getText().toString();
                String email = useremail.getText().toString();
                String password2 = userpassword2.getText().toString();
                String name = username.getText().toString();
                String password1 = userpassword1.getText().toString();

                if((phonenumber.matches(""))||(email.matches(""))||(password1.matches(""))||(password2.matches(""))||(name.matches(""))){
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(Signup.this);
                    mBuilder.setTitle(R.string.signupemptytitle);
                    mBuilder.setMessage(R.string.signupemptymsg);
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
                if (!password1.equals(password2)) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(Signup.this);
                    mBuilder.setTitle(R.string.passwordissuetitle);
                    mBuilder.setMessage(R.string.passwordissuemsg);
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
                if(!email.contains("@")){
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(Signup.this);
                    mBuilder.setTitle("Email Issue");
                    mBuilder.setMessage("Please enter a valid email address");
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
                    createUser(name, password1,email,phonenumber);
                }
            }
        });
    }

    //sends users info to database helper to process
    public void createUser(String name, String password1, String email, String phonenumber) {
        logged = true;
        rest.insertUser(name, password1, email, phonenumber);
        rest.selectUserAccount();
        Intent myIntent = new Intent(Signup.this, Account.class);
        Signup.this.startActivity(myIntent);
    }

}




