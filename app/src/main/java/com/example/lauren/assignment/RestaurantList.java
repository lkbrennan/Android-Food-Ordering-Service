package com.example.lauren.assignment;

/*
Restaurant list page
shows list of restaurant based on users price and location constraints
 */
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.lauren.assignment.MainActivity.location;
import static com.example.lauren.assignment.MainActivity.amount;
import static com.example.lauren.assignment.MainActivity.rname;

public class RestaurantList extends ListActivity {
    TextView restaurantlist_title;
    Button restlist_homebtn;
    public static ArrayList<String> restlist;
    ArrayAdapter adapt;

    restaurantdbhelper rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        restlist_homebtn = findViewById(R.id.restlist_homebtn);
        restaurantlist_title = findViewById(R.id.restaurantlist_title);

        rest = new restaurantdbhelper(this);

        //create an array list to populate my restaurant list
        restlist = new ArrayList<String>();
        adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, restlist);
        setListAdapter(adapt);
        rest.selectRestaurant(location);

        //show error message if list is empty
        if(restlist.isEmpty()){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(RestaurantList.this);
            mBuilder.setTitle("No Results");
            mBuilder.setMessage("No search results have been found for this area!");
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

        //bring user back to home page
        restlist_homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RestaurantList.this, MainActivity.class);
                RestaurantList.this.startActivity(myIntent);
                location = "";
                amount = 0;
            }
        });

        restaurantlist_title.setText(location);


    }

    //if user clicks item this will bring them to restaurant view page
    protected void onListItemClick(ListView l, View v, int pos, long id){
        rname = (String) l.getItemAtPosition(pos);
        rest.selectRestaurantInfo(rname);
        Intent myIntent = new Intent(RestaurantList.this, RestaurantView.class);
        RestaurantList.this.startActivity(myIntent);
    }
}
