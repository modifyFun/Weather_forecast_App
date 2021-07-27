package com.xianguoliang.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mydb";
    public static final int DB_VERSION = 1;
    private static DatabaseHelper databaseHelper;
    private static String SQL;

    public DatabaseHelper( Context context,String SQL){
        super(context, DB_NAME, null, DB_VERSION);
        this.SQL = SQL;
        Log.v("SQLLLLL",SQL);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static synchronized DatabaseHelper getInstance(Context context, String SQL){

        if (databaseHelper == null){
            databaseHelper = new DatabaseHelper(context,SQL);
        }
        return databaseHelper;
    }
}
