package com.example.lauren.assignment;
/*
Account Page
This Page will be displayed to users after they have logged in/signed up.
It will show all their account information and give them an option to update or delete their account
along with the option to log out
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//imports of static variables needed
import static com.example.lauren.assignment.MainActivity.logged;
import static com.example.lauren.assignment.MainActivity.uname;
import static com.example.lauren.assignment.MainActivity.uemail;
import static com.example.lauren.assignment.MainActivity.uphonenumber;
import static com.example.lauren.assignment.MainActivity.uid;


public class Account extends AppCompatActivity {
    //widgets needed for page
    Button updateaccountbutton;
    Button deleteaccountbutton;
    Button account_logoutbutton;
    Button account_homebutton;
    TextView accountname;
    TextView accountemail;
    TextView accountphone;

    //database helper variable
    restaurantdbhelper rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //instantiating database helper
        rest = new restaurantdbhelper(this);

        //finding widgets
        account_homebutton = findViewById(R.id.account_homebutton);
        account_logoutbutton = findViewById(R.id.account_logoutbutton);
        updateaccountbutton = findViewById(R.id.updateaccountbutton);
        deleteaccountbutton = findViewById(R.id.deleteaccountbutton);
        accountname = findViewById(R.id.accountname);
        accountemail = findViewById(R.id.accountemail);
        accountphone = findViewById(R.id.accountphone);

        //setting text for users info
        //uses static variables taken from database after login
        accountname.setText(uname);
        accountemail.setText(uemail);
        accountphone.setText(uphonenumber);

        //button to bring user back to home page
        account_homebutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(Account.this, MainActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                Account.this.startActivity(myIntent);
            }
        });

        //button to logout user
        account_logoutbutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                logged = false;
                uname = "";
                uemail ="";
                uphonenumber = "";
                Intent myIntent = new Intent(Account.this, LogOut.class);
                Account.this.startActivity(myIntent);
            }
        });

        //button to bring user to page where they can update their account
        updateaccountbutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(Account.this, UpdateAccount.class);
                //myIntent.putExtra("key", value); //Optional parameters
                Account.this.startActivity(myIntent);
            }
        });

        //this button lets users delete their account
        //after they have pressed it a dialog box will appear asking thm to confirm their decision
        deleteaccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Account.this);
                mBuilder.setTitle(R.string.accountdeletetitle);
                mBuilder.setMessage(R.string.accountdeletemsg);
                mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        rest.deleteUser(uid);
                        Intent myIntent = new Intent(Account.this, DeleteAccount.class);
                        Account.this.startActivity(myIntent);
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
        });
    }
}
