package com.nithinkumar.water.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.nithinkumar.water.Converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.nithinkumar.water.DataBase.WaterDBSchema.*;

/**
 * Created by nithinkumar on 6/24/17.
 */

public class WaterDataSource {

    private SQLiteDatabase database;
    private WaterHelper dbHelper;
    Date dateCursor;

    private String[] allColumns = {
            Columns.ID,
            Columns.DATE,
            Columns.WATERTAKEN
    };

    public WaterDataSource(Context context) {
        dbHelper = new WaterHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Water createWaterTakenDetails(int waterTaken, Date date) {
        ContentValues values = new ContentValues();
        values.put(Columns.DATE, Converters.dateToString(date));
        values.put(Columns.WATERTAKEN, String.valueOf(waterTaken));

        long insertId = database.insert(TyperTableName.NAME, null, values);
        Cursor cursor = database.query(TyperTableName.NAME,
                allColumns, Columns.ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();
        Water newTperValues = cursorToWaterTakenDetails(cursor);
        cursor.close();

        return newTperValues;
    }

    public Water updateWaterTakenDetails(Water typerDetails) {
        ContentValues values = new ContentValues();
        values.put(Columns.DATE, String.valueOf(typerDetails.getDate()));
        values.put(Columns.WATERTAKEN, String.valueOf(typerDetails.getWaterTaken()));
        database.update(TyperTableName.NAME, values, Columns.ID + " = " + typerDetails.getId(), null);
        return typerDetails;
    }

    private Water cursorToWaterTakenDetails(Cursor cursor) {
        Water typerDetails = new Water();
        typerDetails.setId(cursor.getLong(0));
        String dateString = cursor.getString(1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            typerDetails.setDate(dateFormat.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        typerDetails.setWaterTaken(cursor.getInt(2));
        return typerDetails;
    }

    public List<Water> getWaterTakenDetails() {
        List<Water> type = new ArrayList();

        open();
        Cursor cursor = database.query(TyperTableName.NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Water count = cursorToWaterTakenDetails(cursor);
            type.add(count);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        close();
        return type;

    }


}
