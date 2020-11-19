package com.example.y2s2mad.T5_Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserInfo.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UsersMaster.Users.TABLE_NAME + " (" +
                       UsersMaster.Users._ID + " INTEGER PRIMARY KEY, " +
                       UsersMaster.Users.COLUMN_NAME_USERNAME + " TEXT," +
                       UsersMaster.Users.COLUMN_NAME_PASSWORD + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void addInfo(String userName, String password) {
        //gets the data repository in write mode
        Log.i("Add Info", "Reached method");
        SQLiteDatabase db = getWritableDatabase();
        Log.i("Add Info", "1");

        //create a new map of values, where column names the keys
        ContentValues values = new ContentValues();
        Log.i("Add Info", "2");
        values.put(UsersMaster.Users.COLUMN_NAME_USERNAME, userName);
        Log.i("Add Info", "3");
        values.put(UsersMaster.Users.COLUMN_NAME_PASSWORD, password);
        Log.i("Add Info", "4");

        //insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UsersMaster.Users.TABLE_NAME, null, values);
        Log.i("Add Info", "Leaving method");
    }

    public List readAllInfo() {
        SQLiteDatabase db = getReadableDatabase();

        /*
            define a projection that specifies which columns from the database you will actually
            use after the query
         */
        String[] projection = {
                UsersMaster.Users._ID,
                UsersMaster.Users.COLUMN_NAME_USERNAME,
                UsersMaster.Users.COLUMN_NAME_PASSWORD
        };

        /*
            filter results WHERE "userName" = 'SLIIT USER'
            String selection = Users.COLUMN_NAME_USERNAME + " = ?";
            String[] selectionArgs = {""};
         */

        //how you want the results sorted in the resulting cursor
        String sortOrder = UsersMaster.Users.COLUMN_NAME_USERNAME + " DESC";

        Cursor cursor = db.query(
                UsersMaster.Users.TABLE_NAME,       //the table to query
                projection,                         //the columns to return
                null,                      //the columns for the WHERE clause
                null,                   //the values for the WHERE clause
                null,                       //don't group the rows
                null,                       //don't filter by row groups
                sortOrder                           //the sort order
        );

        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();

        while(cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_PASSWORD));
            userNames.add(username);
            passwords.add(password);
        }

        return userNames;
    }

    public boolean readInfo(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();

        /*
            define a projection that specifies which columns from the database you will actually
            use after the query
         */
        String[] projection = {
                UsersMaster.Users._ID,
                UsersMaster.Users.COLUMN_NAME_USERNAME,
                UsersMaster.Users.COLUMN_NAME_PASSWORD
        };

        //   filter results WHERE "userName" = username
        String selection = UsersMaster.Users.COLUMN_NAME_USERNAME + " = ?" +
                    UsersMaster.Users.COLUMN_NAME_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};


        Cursor cursor = db.query(
                UsersMaster.Users.TABLE_NAME,       //the table to query
                projection,                         //the columns to return
                selection,                      //the columns for the WHERE clause
                selectionArgs,                   //the values for the WHERE clause
                null,                       //don't group the rows
                null,                       //don't filter by row groups
                null                           //the sort order
        );

        boolean found = false;

        return found;
    }

    public void deleteInfo(String username) {
        SQLiteDatabase db = getReadableDatabase();

        //define WHERE part of query
        String selection = UsersMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";

        //specify arguments in placeholder in order
        String[] selectionArgs = {username};

        //issue SQL statement
        db.delete(UsersMaster.Users.TABLE_NAME, selection, selectionArgs);
    }

    public  void  updateInfo(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();

        //new value for one column
        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_PASSWORD, password);

        //which row to update, based on the username
        String selection = UsersMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = {username};

        int count = db.update(
                UsersMaster.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }
}
