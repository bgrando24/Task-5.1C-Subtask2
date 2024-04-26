package com.example.task_51c_subtask2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Controls user authentication as well as providing an abstraction layer for the auth database
 */
public class AuthManager extends SQLiteOpenHelper {
    static private final String DATABASE_NAME = "auth.db";
    static private final int DATABASE_VERSION = 1;
    static private final String TABLE_NAME = "users";
    static private final String COLUMN_ID = "ID";
    static private final String COLUMN_USERNAME = "USERNAME";
    static private final String COLUMN_PASSWORD = "PASSWORD";

//    currently authed user
    static private int authedUserId;

    //    set authenticated user
    public void setAuthedUser(int userId) {
        authedUserId = userId;
    }

    public AuthManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    required as part of SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)");
    }

//    required as part of SQLiteOpenHelper
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

//    add user
    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        long results = db.insert(TABLE_NAME, null, contentValues);
        return results != -1;
    }

//    check if user exists
    public boolean userExists(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        return db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null).getCount() > 0;
    }

    public int getUserId(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
    }
}
