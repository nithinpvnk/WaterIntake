package com.nithinkumar.water.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nithinkumar on 6/24/17.
 */

public class WaterHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    private static final String DATABASE_NAME = "waterConsumptionHistory.db";

    public WaterHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String test = "create table " + WaterDBSchema.TyperTableName.NAME +
                "("
                + WaterDBSchema.Columns.ID + " integer primary key autoincrement, "
                + WaterDBSchema.Columns.DATE + " text not null, "
                + WaterDBSchema.Columns.WATERTAKEN + " text not null); ";
        db.execSQL(test);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(WaterHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + WaterDBSchema.TyperTableName.NAME);
        onCreate(db);
    }

}
