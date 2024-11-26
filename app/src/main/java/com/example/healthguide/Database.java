package com.example.healthguide;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;


//sqlitedbase is a class that provides methods for accessing and manipulating a SQLite db.
//The getReadableDatabase() method is part of the Sqliteopenhelper class
//helper class simplifies database creation and version management
public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table users(username text, email text , password text)";
        db.execSQL(query1);          //using object db we execute sql to create table in sqlite database

        String query2 = "create table Cart(username text , product text , price float , order_type text)";
        db.execSQL(query2);

        String query3 = "create table placeOrder(username text , fullname text , address text , contact text ,pin int, date text , time text , totalCost float , order_type text) ";
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();         //ContentValues class new object
        cv.put("Username", username);      //(key:column_name , value:variable)
        cv.put("Email", email);
        cv.put("Password", password);

        SQLiteDatabase dbase = getWritableDatabase(); //SQLiteDatabase object    getWritableDatabase() to insert data
        //db.insert("users",null,cv)
        dbase.insert("users",null,cv);
        dbase.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String[2];   //bcz there are 2 ? ie we need 2
        str[0] = username;
        str[1] = password;
//below method returns the sqlitedatabase object that provide read access yo database
        SQLiteDatabase dbase = getReadableDatabase();         //want to only select data fetch the dtaa
        Cursor c = dbase.rawQuery("select * from users where username=? and password=?", str);
//username and password are the column names we took from table we created above so write their spellings correctly
        if(c.moveToFirst()) {       //any record is found
            result = 1;           //user exist
        }
        return result;
    }

    public void addtocart(String username,String product , float price , String order_type){
        ContentValues cv = new ContentValues();  //cv--- new object of ContentValues
        cv.put("username" , username);    //put all these values into cv
        cv.put("product",product);//Ensure that column names in the ContentValues match the column names in your database schema
        cv.put("price",price); // not always true
        cv.put("order_type" , order_type);

        SQLiteDatabase dbdata = getWritableDatabase();
        dbdata.insert("Cart",null,cv);  //insert cv object into cart table
        dbdata.close();  //close db connection
    }

//    public void addtocart(String username, String product, float price, String order_type) {
//        ContentValues cv = new ContentValues();
//        cv.put("username", username);
//        cv.put("product", product);
//        cv.put("price", price);
//        cv.put("order_type", order_type);
//
//        SQLiteDatabase dbdata = getWritableDatabase();
//        long result = dbdata.insert("Cart", null, cv);
//        if (result == -1) {
//            Log.e("DatabaseError", "Failed to insert data into cart");
//        } else {
//            Log.d("DatabaseSuccess", "Data inserted into cart: " + product + " for " + username);
//        }
//        dbdata.close();
//    }



    public int checkCart(String username , String product){
        int result = 0;
        String str[]=new String[2];
        str[0]= username ;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from Cart where username =? and product =?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

    public void deleteItems(String username , String product_type){
        String str[]=new String[2];
        str[0] = username;
        str[1]= product_type;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Cart", "username=? and order_type=?", str);
        db.close();
    }


    public ArrayList CartData(String username , String order_type){
        ArrayList<String> arr = new ArrayList<>();  // this is a blank array list
        SQLiteDatabase db = getReadableDatabase();  //object of
        String str[] = new String[2];   //array of string with length 2
        str[0]=username;
        str[1]=order_type;
        Cursor c = db.rawQuery("select * from Cart where username =? and order_type =?",str);
        if(c.moveToFirst()){
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product+"$"+price);  //stores the data in array arr
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void placeorder(String username , String name, String add , String contact , int pin ,
                           String date , String time , float totalCost , String order_type){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname",name);
        cv.put("address", add);
        cv.put("contact" , contact);
        cv.put("pin",pin);
        cv.put("date", date);
        cv.put("time",time);
        cv.put("totalCost",totalCost);
        cv.put("order_type",order_type);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("placeOrder",null,cv);
        db.close();
    }

    public ArrayList getOrderData(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase DB = getReadableDatabase();
        String str[]=new String[1];
        str[0]=username;
        Cursor c=DB.rawQuery("select *from placeorder where username=?",str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+
                        "$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7));
            }while(c.moveToNext());
        }
        DB.close();
        return arr;
    }

    public int checkAppointment(String username , String fullname , String address , String contact , String date , String time){
        int result =0;
        String str[]=new String[6];
        str[0]=username;
        str[1]=fullname;
        str[2]=address;
        str[3]=contact;
        str[4]=date;
        str[5]=time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from placeorder where username=? and fullname=? and address=? and contact=? and date=? and time=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

}

