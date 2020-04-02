package com.example.glucoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "register2.db";
    public static final String TABLE_User = "registeruser1";
    public static final String TABLE_MED = "medicine";
    public static final String TABLE_SHOTS = "insulin";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "name";
    public static final String COL_3 = "lastname";
    public static final String COL_4 = "username";
    public static final String COL_5 = "password";
    public static final String COL_6 = "telephone";
    public static final String med_1 = "ID1";
    public static final String med_2 = "med_name";
    public static final String med_3 = "med_time";
    public static final String med_4="userm";
    public static final String ins_1 = "ID2";
    public static final String ins_2 = "ins_name";
    public static final String ins_3 = "ins_time";
    public static final String ins_4 = "useri";
    public static final String table1 = "CREATE TABLE registeruser1 (ID INTEGER PRIMARY  KEY AUTOINCREMENT, name TEXT, lastname TEXT, username TEXT, password TEXT, telephone TEXT)";
    public static final String table2 = "CREATE TABLE medicine (ID1 INTEGER PRIMARY  KEY , med_name TEXT, med_time TEXT, userm TEXT)";
    public static final String table3 = "CREATE TABLE insulin (ID2 INTEGER PRIMARY  KEY , ins_name TEXT, ins_time TEXT, useri TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        //  db.execSQL("CREATE TABLE registeruser1 (ID INTEGER PRIMARY  KEY AUTOINCREMENT, name TEXT, lastname TEXT, username TEXT, password TEXT, telephone TEXT)");
        db.execSQL(table1);
        db.execSQL(table2);
        db.execSQL(table3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_User);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_MED);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_SHOTS);

        this.onCreate(sqLiteDatabase);
    }

    public long addUser(String name, String lastname, String username, String password, String telephone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("lastname", lastname);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("telephone", telephone);

        long res = db.insert("registeruser1", null, contentValues);
        db.close();
        return res;
    }

    public long addmed(String med, String time,String user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("med_name", med);
        //Time.valueOf(time);
        contentValues.put("med_time", time);
        contentValues.put("userm", user);
        long res = db.insert("medicine", null, contentValues);
        db.close();
        return res;
    }

    public long addins( String ins_name, String time,String user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ins_name", ins_name);

        contentValues.put("ins_time", time);
        contentValues.put("useri", user);
        long res = db.insert("insulin", null, contentValues);
        db.close();
        return res;
    }


    public boolean checkUser(String username, String password) {
        String[] columns = {COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_4 + "=?" + " and " + COL_5 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_User, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0)
            return true;
        else
            return false;
    }

public long return_id(String username) {
    String[] columns = {COL_1};
    SQLiteDatabase db = getReadableDatabase();
    String selection = COL_4 + "=?";
    String[] selectionArgs = {username};
    Cursor cursor = db.query(TABLE_User, columns, selection, selectionArgs, null, null, null);
    int count = cursor.getCount();
    cursor.close();
    db.close();

    long number = 0;
    if (count > 0)
        number= cursor.getLong(0);
    return number;
}
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name,lastname FROM "+ TABLE_User;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(COL_2)));
            user.put("time",cursor.getString(cursor.getColumnIndex(COL_3)));

            userList.add(user);
        }
        return  userList;
    }

    public ArrayList<HashMap<String, String>> GetMeds(String s){
        SQLiteDatabase db = this.getWritableDatabase();

        String[] col=new String[]{med_2,med_3};  // your column which data u want to retrive if id is same
        Cursor cursor=db.query(TABLE_MED, col, med_4+"='"+s+"'",null, null, null, null);
        System.out.println(cursor);
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
if(cursor!=null) {




        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(med_2)));
            user.put("time",cursor.getString(cursor.getColumnIndex(med_3)));

            userList.add(user);
        }}

        return  userList;}

    public ArrayList<HashMap<String, String>> GetIns(String s){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] col=new String[]{ins_2,ins_3};
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
       // String query = "SELECT ins_name,ins_time FROM "+ TABLE_SHOTS;
        Cursor cursor=db.query(TABLE_SHOTS, col, ins_4+"='"+s+"'",null, null, null, null);
        if(cursor!=null) {    while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(ins_2)));
            user.put("time",cursor.getString(cursor.getColumnIndex(ins_3)));

            userList.add(user);
        }}
        return  userList;
    }}





















