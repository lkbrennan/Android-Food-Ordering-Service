package com.example.lauren.assignment;

/*
my database helper
holds my restaurant, user and review tables
currently on version 9
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.lauren.assignment.MainActivity.amount;
import static com.example.lauren.assignment.MainActivity.logged;
import static com.example.lauren.assignment.MainActivity.ravg;
import static com.example.lauren.assignment.MainActivity.rid;
import static com.example.lauren.assignment.MainActivity.rphonenum;
import static com.example.lauren.assignment.MainActivity.uname;
import static com.example.lauren.assignment.MainActivity.uemail;
import static com.example.lauren.assignment.MainActivity.uphonenumber;
import static com.example.lauren.assignment.MainActivity.uid;
import static com.example.lauren.assignment.RestaurantList.restlist;
import static com.example.lauren.assignment.Review.reviewlist;

public class restaurantdbhelper extends SQLiteOpenHelper {

    //column and table names
    private SQLiteDatabase database;
    private static final String welpdb = "welp.db";
    public static final int version = 9;
    private static final String restauranttable = "restaurant";
    private static final String column_rid = "rid";
    private static final String column_rname = "rname";
    private static final String column_rlocation = "rlocation";
    private static final String column_rphonenumber = "rphone";
    private static final String column_ravgprice = "ravgprice";
    private static final String usertable = "users";
    private static final String column_uid = "userid";
    private static final String column_uname = "username";
    private static final String column_userpassword = "userpassword";
    private static final String column_useremail = "useremail";
    private static final String column_userphone = "userphone";
    private static final String reviewtable = "review";
    private static final String column_reviewid = "restreviewid";
    private static final String column_review = "restreview";

    public restaurantdbhelper(Context context){
        super(context, welpdb, null, version);
    }

    //creation of tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+restauranttable+"( "+column_rid+" INTEGER PRIMARY KEY AUTOINCREMENT, " +column_rname+" VARCHAR,"+column_rlocation+" VARCHAR,"+column_rphonenumber+" VARCHAR," +column_ravgprice+" INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+usertable+"( "+column_uid+" INTEGER PRIMARY KEY AUTOINCREMENT, " +column_uname+" VARCHAR,"+column_userpassword+" VARCHAR,"+column_useremail+" VARCHAR,"+column_userphone+" VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+reviewtable+"("+column_reviewid+" INTEGER PRIMARY KEY AUTOINCREMENT,"+column_rid+" INTEGER,"+column_review+" VARCHAR);");
        Log.d("CREATE","create DB");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE restaurant;");
        onCreate(db);
    }


    public void insertUser( String name, String password, String email, String phone) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_uname, name);
        contentValues.put(column_userpassword, password);
        contentValues.put(column_useremail,email);
        contentValues.put(column_userphone, phone);
        database.insert(usertable, null, contentValues);
        Cursor cursor = database.rawQuery("SELECT userid FROM users WHERE useremail = "+"'"+email+"'"+" AND userpassword = "+"'"+password+"'"+";",null);
        cursor.moveToFirst();
        uid = cursor.getInt(0);
        cursor.close();
        database.close();
    }

    /*public void insertRest(int id, String rname, String rlocation, String rnum, int avg) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_rid, id);
        contentValues.put(column_rname, rname);
        contentValues.put(column_rlocation, rlocation);
        contentValues.put(column_rphonenumber,rnum);
        contentValues.put(column_ravgprice, avg);
        database.insert(restauranttable, null, contentValues);
        database.close();
        Log.d("INSERT","restaurant inserted");
    }
 rest.insertRest(0,"Great Wall","Artane","01123123",15);
        rest.insertRest(1,"Kebab House","Artane","01123124",5);
        rest.insertRest(2,"Pizza Place","Artane","01123125",10);
        rest.insertRest(3,"China Town","Artane","01123126",15);
        rest.insertRest(4,"Pizza Hut","Artane","01123127",15);
        rest.insertRest(5,"Vincenzos","Drimnagh","01123129",5);
        rest.insertRest(6,"New Dehli","Drimnagh","01121123",15);
        rest.insertRest(7,"Delicious Deserts","Drimnagh","01122123",10);
        rest.insertRest(8,"BBQ House","Rathgar","01124123",15);
        rest.insertRest(9,"Jade Garden","Rathgar","01125123",15);
        rest.insertRest(10,"KFC","Rathgar","01126123",10);
        rest.insertRest(11,"Beshoff Bros","Rathgar","01127123",5);
        rest.insertRest(12,"Marios Italian","Rathgar","01127123",10);
        rest.insertRest(13,"Burger King","Rathgar","01128123",15);
        rest.insertRest(14,"Milanos","Tallaght","0113023",15);
        rest.insertRest(15,"China Palace","Tallaght","01654321",10);
        rest.insertRest(16,"Taj Mahal","Tallaght","0185752",10);
        rest.insertRest(17,"PJs Plaice","Tallaght","01840275",5);
        rest.insertRest(18,"Aussie Outback","Tallaght","01927592",15);
        rest.insertRest(19,"McDonalds","Tallaght","01000375",5);
        rest.insertRest(20,"Eddie Rockets","Blanchardstown","01394575",15);
        rest.insertRest(21,"Wetherspoons","Blanchardstown","01457348",15);
        rest.insertRest(22,"Red Dragon","Blanchardstown","01134534",15);
        rest.insertRest(23,"Garden Palace","Blanchardstown","01345326",10);
        rest.insertRest(24,"Jade Palace","Blanchardstown","01983455",10);
        rest.insertRest(25,"Bobs Burger","Blanchardstown","01018565",5);
        rest.insertRest(26,"Krusty Burger","Blanchardstown","00184643",5);
        rest.insertRest(27,"Freddy Fazbears Pizza","Artane","01787394",10);
        rest.insertRest(28,"Good Burger","Crumlin","01345576",5);
        rest.insertRest(29,"Krusty Krab","Crumlin","01972355",15);
        rest.insertRest(30,"Los Pollos Hermanos","Crumlin","01823873",10);
        rest.insertRest(31,"Bobs Burgers","Crumlin","01723673",10);
        rest.insertRest(32,"Mels Diner","Crumlin","017832948",5);*/

    public boolean selectUserLogin(String email, String password){
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT useremail,userpassword,userid FROM users WHERE useremail LIKE "+"'"+email+"'"+" AND userpassword LIKE "+"'"+password+"'"+";",null);
        cursor.moveToFirst();
        if(cursor.getCount()==0){
            cursor.close();
            return false;
        }
        else{
            uid = cursor.getInt(2);
            cursor.close();
            return true;
        }
    }

    public void selectUserAccount(){
        database = getReadableDatabase();
        Cursor cursor= database.rawQuery("SELECT username,useremail,userphone FROM users WHERE userid LIKE "+uid+";",null);
        cursor.moveToFirst();
        uname = cursor.getString(0);
        uemail = cursor.getString(1);
        uphonenumber = cursor.getString(2);
        cursor.close();
    }

    public void updateUser(String name, String email, String phonenum, int id){
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("UPDATE users SET  username = "+"'"+name+"'"+", useremail = "+"'"+email+"'"+", userphone = "+"'"+phonenum+"'"+" WHERE userid = "+id+";",null);
        cursor.moveToFirst();
        uname = name;
        uemail = email;
        uphonenumber = phonenum;
        cursor.close();
        Log.e("UPDATE","success");
    }

    public void deleteUser(int id){
        database = getReadableDatabase();
        database.execSQL("DELETE FROM users WHERE userid = "+id+";");
        logged = false;
        uname = "";
        uemail = "";
        uphonenumber = "";
        database.close();
    }

    public void selectRestaurant(String location){
        database = getReadableDatabase();
        if(amount == 0) {
            Cursor cursor = database.rawQuery("SELECT rname FROM restaurant WHERE rlocation LIKE " + "'" + location + "'" + ";", null);
            cursor.moveToFirst();
            restlist.add(cursor.getString(0));
            while (cursor.moveToNext()) {
                restlist.add(cursor.getString(0));
            }
            cursor.close();
        }
        if((amount == 5)||(amount == 10)||(amount == 15)) {
            Cursor cursor = database.rawQuery("SELECT rname FROM restaurant WHERE rlocation LIKE " + "'" + location + "'" + "AND ravgprice = "+amount+";", null);
            cursor.moveToFirst();
            restlist.add(cursor.getString(0));
            while (cursor.moveToNext()) {
                restlist.add(cursor.getString(0));
            }
            cursor.close();
        }
    }

    public void selectRestaurantInfo(String name){
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT rid, ravgprice, rphone FROM restaurant WHERE rname LIKE " + "'" + name + "'" + ";", null);
        cursor.moveToFirst();
        rid = cursor.getInt(0);
        ravg = cursor.getInt(1);
        rphonenum = cursor.getString(2);
        cursor.close();
    }

    public void selectRestReview(){
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT restreview FROM review WHERE rid = "+rid+";", null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false) {
            reviewlist.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void insertRestReview(String review){
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_review, review);
        contentValues.put(column_rid,rid);
        database.insert(reviewtable, null, contentValues);
        database.close();
    }
}
