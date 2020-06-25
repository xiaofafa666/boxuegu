package com.example.xiaofafa.boxuegu2.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    public  static String U_USERINFO = "userinfo";
    public static String DB_NAME = "yidong2.db";
    public static final int DB_VERSION = 1;

    public SQLiteHelper(@Nullable Context context, @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, 数据库名称 name, null, 版本version);
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_USERINFO + "(_id INTEGER PRIMARY KEY "+
                "AUTOINCREMENT,"
                 +"userName VARCHAR , nickName VARCHAR, sex VARCHAR, signature VARCHAR)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + U_USERINFO);
        onCreate(db);
    }
}
