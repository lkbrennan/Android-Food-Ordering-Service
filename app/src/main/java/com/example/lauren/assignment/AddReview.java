package com.example.lauren.assignment;

/*
Add Review Page
This page allows users to enter a review about a restaurant
It has a multi line edittext widget which lets users enter in their review
This review will then be added to the database table "Review"
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddReview extends AppCompatActivity {
    //widgets needed for page
    Button reviewcancel;
    Button addreview;
    EditText reviewbox;

    //database helper variable
    restaurantdbhelper rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        //finding widgets
        reviewcancel = findViewById(R.id.reviewcancel);
        addreview = findViewById(R.id.addreviewbutton);
        reviewbox = findViewById(R.id.reviewbox);

        //instantiating database helper
        rest = new restaurantdbhelper(this);

        //button to cancel review and go back to previos page
        reviewcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(AddReview.this, Review.class);
                AddReview.this.startActivity(myIntent);
            }
        });

        //button which will add the users review to the database
        //if the edittext is empty a dialog box will appear asking for the user to enter in a review
        addreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String review = reviewbox.getText().toString();
                if(review.matches("")){
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddReview.this);
                    mBuilder.setTitle(R.string.emptyreviewtitle);
                    mBuilder.setMessage(R.string.emptyreviewmsg);
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
                rest.insertRestReview(review);
                Intent myIntent = new Intent(AddReview.this, Review.class);
                AddReview.this.startActivity(myIntent);
            }
        });
    }
}
