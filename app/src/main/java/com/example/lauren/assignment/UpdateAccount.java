package com.example.lauren.assignment;

/*
Update account page
this page allows users to update their account
has a series of edittext boxes with their info prefilled into them
users can change the information in the edittext boxes as they please
if they leave a box empty it display a warning dialog to the user
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.lauren.assignment.MainActivity.uemail;
import static com.example.lauren.assignment.MainActivity.uid;
import static com.example.lauren.assignment.MainActivity.uname;
import static com.example.lauren.assignment.MainActivity.uphonenumber;

public class UpdateAccount extends AppCompatActivity {
    Button updateaccount_cancel;
    Button updateaccount_updateaccount;
    EditText updateaccount_name;
    EditText updateaccount_email;
    EditText updateaccount_phone;

    restaurantdbhelper rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        rest = new restaurantdbhelper(this);
        updateaccount_cancel = findViewById(R.id.updateaccount_cancel);
        updateaccount_updateaccount = findViewById(R.id.updateaccount_updateaccount);
        updateaccount_name = findViewById(R.id.updateaccount_name);
        updateaccount_email = findViewById(R.id.updateaccount_email);
        updateaccount_phone = findViewById(R.id.updateaccount_phone);


        updateaccount_name.setText(uname);
        updateaccount_email.setText(uemail);
        updateaccount_phone.setText(uphonenumber);

        updateaccount_updateaccount.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String useremail = updateaccount_email.getText().toString();
                String username = updateaccount_name.getText().toString();
                String userphone = updateaccount_phone.getText().toString();

                //if anything is empty display error dialog
                if((useremail.matches(""))||(username.matches(""))||(userphone.matches(""))) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(UpdateAccount.this);
                    mBuilder.setTitle(R.string.updateaccountemptytitle);
                    mBuilder.setMessage(R.string.updateaccountemptymsg);
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
                    rest.updateUser(username,useremail,userphone,uid);
                    rest.selectUserAccount();
                    Intent myIntent = new Intent(UpdateAccount.this, Account.class);
                    UpdateAccount.this.startActivity(myIntent);
                }
            }
        });

        //brings user back to previous page
        updateaccount_cancel.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(UpdateAccount.this, Account.class);
                //myIntent.putExtra("key", value); //Optional parameters
                UpdateAccount.this.startActivity(myIntent);
            }
        });
    }
}
