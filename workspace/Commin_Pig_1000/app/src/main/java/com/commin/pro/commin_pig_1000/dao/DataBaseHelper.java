package com.commin.pro.commin_pig_1000.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.commin.pro.commin_pig_1000.model.Model4Chart;
import com.commin.pro.commin_pig_1000.util.UtilDB;

import java.util.ArrayList;

/**
 * Created by user on 2016-10-19.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "DEPOSIT";
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
                "type INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String value, String date, int type) {
        SQLiteDatabase db = getWritableDatabase();
        UtilDB.execSQL(db, "INSERT INTO " + TABLE_NAME + " VALUES (null,'" + value + "','" +
                date + "'," +
                type + ");");
    }

    public ArrayList<Model4Chart> getResult() {
        SQLiteDatabase db = getReadableDatabase();
        Model4Chart model = new Model4Chart();
        ArrayList<Model4Chart> models = new ArrayList<Model4Chart>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while(cursor.moveToNext()){
            String value = cursor.getString(1);
            String date = cursor.getString(2);
            int type = cursor.getInt(3);
            if(type ==1){//

            } else if (type ==2) {//

            }

        }

        return models;
    }


}
