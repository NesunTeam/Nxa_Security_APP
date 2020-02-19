package com.nesun.sec.apps.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class PrivateFileDb extends SQLiteOpenHelper {

    public static final String DB_NAME = "privatefile.db";
    public static final String TB_PRIVATEFILE_NAME = "privatefile";

    // column
    public static final String PRIVATEFILE_ID = "_id";
    public static final String PRIVATEFILE_NAME = "name";
    public static final String PRIVATEFILE_PATH = "path";
    public static final String PRIVATEFILE_TIME = "time";
    public static final String PRIVATEFILE_SIZE = "size";
    public static final String PRIVATEFILE_TYPE = "type";

    public PrivateFileDb(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TB_PRIVATEFILE_NAME + " ("
                + PRIVATEFILE_ID + " integer primary key autoincrement, "
                + PRIVATEFILE_PATH + " text not null, "
                + PRIVATEFILE_NAME + " text not null, "
                + PRIVATEFILE_TIME + " text not null, "
                + PRIVATEFILE_TYPE + " integer not null, "
                + PRIVATEFILE_SIZE + " text not null);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
