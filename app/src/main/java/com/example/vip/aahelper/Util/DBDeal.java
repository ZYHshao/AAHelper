package com.example.vip.aahelper.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vip.aahelper.Model.PeopleModel;

import java.util.ArrayList;

/**
 * Created by vip on 16/7/25.
 */
public class DBDeal {
    private Context context;
    public DBDeal(Context context){
        this.context = context;
    }
    /**
     * 添加一个人员
     *
     * */
    public void addPeople(PeopleModel people){
        DBHelp help = new DBHelp(context);
        SQLiteDatabase db = help.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",people.name);
        values.put("time",people.time);
        values.put("isAble",people.isAble);
        db.insert("people",null,values);
        db.close();
        help.close();
    }
    /**
     * 更新一个人员名称
     *
     */
    public void updatePeopel(PeopleModel people){
        DBHelp help = new DBHelp(context);
        SQLiteDatabase db = help.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",people.name);
        values.put("time",people.time);
        values.put("isAble",people.isAble);
        String whereStr = "_id="+people._id;
        db.update("people",values,whereStr,null);
        db.close();
        help.close();
    }
    /*
    * 删除一个用户
    * 注意 是逻辑删除 不是物理删除
    *
    * */
    public void removePeople(PeopleModel people){
        DBHelp help = new DBHelp(context);
        SQLiteDatabase db = help.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",people.name);
        values.put("time",people.time);
        values.put("isAble",0);
        String whereStr = "_id="+people._id;
        db.update("people",values,whereStr,null);
        db.close();
        help.close();
    }
    /*
    * 获取所有有效的人员list
    *
    * */
    public ArrayList<PeopleModel> getAllAbledPeople(){
        DBHelp help = new DBHelp(context);
        SQLiteDatabase db = help.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from people where isAble=1 order by time",null);
        ArrayList<PeopleModel> array = new ArrayList<PeopleModel>();
        while (cursor.moveToNext()){
            PeopleModel people = new PeopleModel();
            people._id = cursor.getInt(cursor.getColumnIndex("_id"));
            people.isAble = cursor.getInt(cursor.getColumnIndex("isAble"));
            people.time = cursor.getLong(cursor.getColumnIndex("time"));
            people.name = cursor.getString(cursor.getColumnIndex("name"));
            array.add(people);
        }
        return array;
    }
}
