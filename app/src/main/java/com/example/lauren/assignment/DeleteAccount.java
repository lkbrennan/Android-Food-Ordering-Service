package com.example.lauren.assignment;
/*
Delete Account Page
This will be shown after user has confirmed they want their account deleted
This page just confirms to the user that their account has been deleted from the database
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeleteAccount extends AppCompatActivity {
    Button deleteaccount_homebutton;
    Button deleteaccount_loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        //finding widget needed in page
        deleteaccount_homebutton = findViewById(R.id.deleteaccount_homebutton);
        deleteaccount_loginbutton = findViewById(R.id.deleteaccount_loginbutton);

        //button to bring user to home/main page
        deleteaccount_homebutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(DeleteAccount.this, MainActivity.class);
                DeleteAccount.this.startActivity(myIntent);
            }
        });

        //button to allow user to login
        deleteaccount_loginbutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(DeleteAccount.this, Login.class);
                //myIntent.putExtra("key", value); //Optional parameters
                DeleteAccount.this.startActivity(myIntent);
            }
        });
    }
}
