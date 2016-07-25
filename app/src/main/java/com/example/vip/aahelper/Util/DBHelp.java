package com.example.vip.aahelper.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vip on 16/7/25.
 */
public class DBHelp extends SQLiteOpenHelper {
    public DBHelp(Context context){
        //创建数据库
        super(context,"AAHelp.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //进行建表
        //创建人员管理表 id:编号 name:名称 isAble:是否删除 0表示删除 1表示有效 time:添加时间
        String sql = "create table people(_id integer primary key autoincrement,"+
                "name text,"+
                "isAble int,"+
                "time long)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
