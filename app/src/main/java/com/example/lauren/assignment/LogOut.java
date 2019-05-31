package com.example.lauren.assignment;

/*
Log Out Page
This page is displayed after user has logged out
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogOut extends AppCompatActivity {
    Button logout_homebutton;
    Button logout_loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);

        //finds widgets
        logout_homebutton = findViewById(R.id.deleteaccount_homebutton);
        logout_loginbutton = findViewById(R.id.logout_loginbutton);

        //button to bring user to home page
        logout_homebutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(LogOut.this, MainActivity.class);
                LogOut.this.startActivity(myIntent);
            }
        });

        //button to bring user to log in page
        logout_loginbutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(LogOut.this, Login.class);
                //myIntent.putExtra("key", value); //Optional parameters
                LogOut.this.startActivity(myIntent);
            }
        });
    }
}
