package com.example.lauren.assignment;

/*
Review List Page
this page shows the reviews of a restaurant in a list format
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class Review extends ListActivity {

    Button reviewback;
    Button addreview;
    public static ArrayList<String> reviewlist;
    ArrayAdapter adapt;

    restaurantdbhelper rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        reviewback = findViewById(R.id.reviewback);
        addreview = findViewById(R.id.addreviewbutton);

        rest = new restaurantdbhelper(this);

        //create array list to populate review list
        reviewlist = new ArrayList<String>();
        adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, reviewlist);
        setListAdapter(adapt);
        rest.selectRestReview();

        //bring user back to previous page
        reviewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Review.this, RestaurantView.class);
                Review.this.startActivity(myIntent);
            }
        });

        //brings user to add review page
        addreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Review.this, AddReview.class);
                Review.this.startActivity(myIntent);
            }
        });

    }
}
