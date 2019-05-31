package com.example.lauren.assignment;

/*
Main/Home Page
This is the page shown to users when they open the app
it gives them the option to search for restaurant in a certain area
sort by price
it allows gives them the option to log into their account if they are not logged in
or
gives them the option to view their account page if they are logged in
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    //static variables to hold user/restaurant information
    static boolean logged = false;
    static String location;
    static int rid;
    static String rname;
    static String rphonenum;
    static int ravg;
    static int amount;
    static String uname;
    static String uemail;
    static String uphonenumber;
    static int uid;

    Button locInputButton;
    Button Account;
    EditText locInput;
    Spinner amountspinner;
    restaurantdbhelper rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find widgets
        locInput = findViewById(R.id.locInput);
        locInputButton = findViewById(R.id.locInputButton);
        amountspinner = findViewById(R.id.amountspinner);
        Account = findViewById(R.id.Account);


        //instantiate database helper
        rest = new restaurantdbhelper(this);

        //searches for restaurants based on location inputted into the edittext field
        locInputButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                amount = 5*(amountspinner.getSelectedItemPosition());
                location = locInput.getText().toString();
                Intent myIntent = new Intent(MainActivity.this, RestaurantList.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        //brings user to account page or log in page depending on whether they are logged in or not
        Account.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(logged == false) {
                    Intent myIntent = new Intent(MainActivity.this, Login.class);
                    //myIntent.putExtra("key", value); //Optional parameters
                    MainActivity.this.startActivity(myIntent);
                }
                if(logged == true){
                    Intent myIntent = new Intent(MainActivity.this, Account.class);
                    MainActivity.this.startActivity(myIntent);
                }
            }
        });
    }
}
