package com.example.task_51c_subtask2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Provides an abstraction layer to the video database
 */
public class DataManager extends SQLiteOpenHelper {
    static private final String DATABASE_NAME = "videos.db";
    static private final int DATABASE_VERSION = 1;
    static private final String TABLE_NAME = "videos";
    static private final String COLUMN_ID = "ID";
    static private final String COLUMN_TITLE = "URL";
    static private final String COLUMN_USER_ID = "USER_ID";

    public DataManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    required as part of SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_USER_ID + " INTEGER)");
    }

//    required as part of SQLiteOpenHelper
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


//    add video
    public boolean insertVideo(String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, url);
        contentValues.put(COLUMN_USER_ID, 1);
        long results = db.insert(TABLE_NAME, null, contentValues);
        return results != -1;
    }

//    get all videos
    public Cursor getAllVideos(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE USER_ID = ?", new String[]{String.valueOf(userId)});
    }


}
