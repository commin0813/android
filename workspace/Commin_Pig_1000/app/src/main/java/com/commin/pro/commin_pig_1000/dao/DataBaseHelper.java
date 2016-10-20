package com.commin.pro.commin_pig_1000.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.commin.pro.commin_pig_1000.ApplicationProperty;
import com.commin.pro.commin_pig_1000.model.Model4Chart;
import com.commin.pro.commin_pig_1000.util.UtilDB;

import java.util.ArrayList;

/**
 * Created by user on 2016-10-19.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "DEPOSIT";
    public static final String TABLE_NAME_RESULT = "DEPOSIT_RESULT";
    private Context context;

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "value TEXT, " +
                "date TEXT, " +
                "type INTEGER," +
                "status INTEGER);");
        db.execSQL("CREATE TABLE " + TABLE_NAME_RESULT + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "value TEXT, " +
                "date TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void updateSTATUS(int change_value) {
        SQLiteDatabase db = getWritableDatabase();
        UtilDB.execSQL(db, "UPDATE " + TABLE_NAME + " SET status=" + change_value + ";");
    }

    public void insert(String value, String date, int type) {
        SQLiteDatabase db = getWritableDatabase();
        UtilDB.execSQL(db, "INSERT INTO " + TABLE_NAME + " VALUES (null,'" + value + "','" +
                date + "'," +
                type + "," +
                ApplicationProperty.DEPOSIT_STATUS_NORMAL + ");");
    }

    public void insert_result(String value, String date) {
        SQLiteDatabase db = getWritableDatabase();
        UtilDB.execSQL(db, "INSERT INTO " + TABLE_NAME_RESULT + " VALUES (null,'" + value + "','" +
                date + "');");
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        UtilDB.execSQL(db, "DELETE FROM " + TABLE_NAME + ";");
        SQLiteDatabase db2 = getWritableDatabase();
        UtilDB.execSQL(db2, "DELETE FROM " + TABLE_NAME_RESULT + ";");
    }

    public void deleteByKeyValue(int key) {
        SQLiteDatabase db = getWritableDatabase();
        UtilDB.execSQL(db, "DELETE FROM " + TABLE_NAME + " WHERE _id=" + key + ";");
    }

    public ArrayList<Model4Chart> getResult_normal() {//query
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Model4Chart> models = new ArrayList<Model4Chart>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY _id DESC", null);
        while (cursor.moveToNext()) {
            Model4Chart model = new Model4Chart();
            int id = cursor.getInt(0);
            String value = cursor.getString(1);
            String date = cursor.getString(2);
            int type = cursor.getInt(3);
            int status = cursor.getInt(4);
            if (type == 1) {//
                model.setId(id);
                model.setDeposit_value(value);
                model.setDeposit_date(date);
                model.setType(type);
                model.setStatus(status);
            } else if (type == 2) {//
                model.setId(id);
                model.setDeposit_value(value);
                model.setDeposit_date(date);
                model.setType(type);
                model.setStatus(status);
            }
            models.add(model);

        }
        db.close();
        return models;
    }

    public ArrayList<Model4Chart> getResult_total() {//query
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Model4Chart> models = new ArrayList<Model4Chart>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_RESULT, null);
        while (cursor.moveToNext()) {
            Model4Chart model = new Model4Chart();
            String value = cursor.getString(1);
            String date = cursor.getString(2);
            model.setTotal_deposit_value(value);
            model.setDeposit_date(date);
            models.add(model);
        }
        db.close();
        return models;
    }


}
