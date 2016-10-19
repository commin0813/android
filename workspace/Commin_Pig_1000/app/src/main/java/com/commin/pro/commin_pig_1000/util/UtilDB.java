package com.commin.pro.commin_pig_1000.util;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by user on 2016-10-19.
 */
public class UtilDB {
    public static void execSQL(SQLiteDatabase db,String sql){
        db.execSQL(sql);
        db.close();
    }

}
