package com.nithinkumar.water.DataBase;

import java.util.Date;

/**
 * Created by nithinkumar on 6/24/17.
 */

public class Water {

    private long mId;
    private Date mDate;
    private int mWaterTaken;


    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getWaterTaken() {
        return mWaterTaken;
    }

    public void setWaterTaken(int waterTaken) {
        mWaterTaken = waterTaken;
    }
}
