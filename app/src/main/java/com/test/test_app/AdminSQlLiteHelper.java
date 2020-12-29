package com.test.test_app;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQlLiteHelper extends SQLiteOpenHelper{
    public AdminSQlLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table demo (id int, descript text, value real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // New updates to see
        // This method is called whenever there is an updation in the database like modifying the table structure, adding constraints to the database, etc.
        db.execSQL("DROP TABLE IF EXISTS demo");
        // Create tables again
        onCreate(db);
    }
}
