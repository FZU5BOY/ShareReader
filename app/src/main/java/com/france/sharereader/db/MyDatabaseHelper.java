package com.france.sharereader.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.france.sharereader.util.LogUtil;

/**
 * Created by Administrator on 2015/10/30.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper
{
    final String CREATE_TABLE_SQL =
            "create table dict(_id integer primary " +
                    "key autoincrement , word , detail)";
    public MyDatabaseHelper(Context context, String name, int version)
    {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // 第一次使用数据库时自动建表
        db.execSQL(CREATE_TABLE_SQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db
            , int oldVersion, int newVersion)
    {
        LogUtil.ShowLog("--------onUpdate Called--------"
                + oldVersion + "--->" + newVersion);
    }
}
