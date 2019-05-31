package com.example.lauren.assignment;

/*
Restaurant view page
shows information about restaurant chosen by user
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.lauren.assignment.MainActivity.location;
import static com.example.lauren.assignment.MainActivity.ravg;
import static com.example.lauren.assignment.MainActivity.rname;
import static com.example.lauren.assignment.MainActivity.rphonenum;

public class RestaurantView extends AppCompatActivity {

    Button backbutton;
    Button reviewbutton;
    TextView restname;
    TextView locview;
    TextView phoneview;
    TextView priceview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_view);

        backbutton = findViewById(R.id.backbutton);
        reviewbutton = findViewById(R.id.reviewsbutton);
        restname = findViewById(R.id.RestName);
        locview = findViewById(R.id.locationview);
        phoneview = findViewById(R.id.phoneview);
        priceview = findViewById(R.id.priceview);

        restname.setText(rname);
        locview.setText(location);
        phoneview.setText(rphonenum);
        priceview.setText(Integer.toString(ravg));

        //brig user to restaurant list
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RestaurantView.this, RestaurantList.class);
                RestaurantView.this.startActivity(myIntent);
            }
        });

        //brings user to review list so they can read or add review
        reviewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RestaurantView.this, Review.class);
                RestaurantView.this.startActivity(myIntent);
            }
        });
    }
}
