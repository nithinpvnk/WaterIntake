package com.nithinkumar.water;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by nithinkumar on 7/21/17.
 */

public class WaterShared {

    private final String name_userValue = "name_userValue";
    private final String height_userValue = "height_userValue";
    private final String weight_userValue = "weight_userValue";
    private final String startTime_userValue = "startTime_userValue";
    private final String endTime_userValue = "endTime_userValue";
    private final String firstRun_app = "firstRun_app";
    private final String waterAdded_current = "waterAdded_current";
    private final String waterCurrentValue_daily = "waterCurrentValue_daily";
    private SharedPreferences mSharedPreferences;


    public WaterShared(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getName_userValue() {
        return mSharedPreferences.getString(name_userValue, "UserName");
    }

    public void setName_userValue(String userName) {
        mSharedPreferences.edit().putString(name_userValue, userName).apply();
    }

    public int getHeight_userValue() {
        return mSharedPreferences.getInt(height_userValue, 00);
    }

    public void setHeight_userValue(int userHeight) {
        mSharedPreferences.edit().putInt(height_userValue, userHeight).apply();
    }

    public int getWeight_userValue() {
        return mSharedPreferences.getInt(weight_userValue, 00);
    }

    public void setWeight_userValue(int userWeight) {
        mSharedPreferences.edit().putInt(weight_userValue, userWeight).apply();
    }

    public String getStartTime_userValue() {
        return mSharedPreferences.getString(startTime_userValue, "");
    }

    public void setStartTime_userValue(String startTime) {
        mSharedPreferences.edit().putString(startTime_userValue, startTime).apply();
    }

    public String getEndTime_userValue() {
        return mSharedPreferences.getString(endTime_userValue, "");
    }

    public void setEndTime_userValue(String endTime) {
        mSharedPreferences.edit().putString(endTime_userValue, endTime).apply();
    }

    public String getFirstRun_app() {
        return mSharedPreferences.getString(firstRun_app, "");
    }

    public void setFirstRun_app(String firstRun) {
        mSharedPreferences.edit().putString(firstRun_app, firstRun).apply();
    }

    public int getWaterAdded_current() {
        return mSharedPreferences.getInt(waterAdded_current, 00);
    }

    public void setWaterAdded_current(int waterAdded) {
        mSharedPreferences.edit().putInt(waterAdded_current, waterAdded).apply();
    }

    public int getWaterCurrentValue_daily() {
        return mSharedPreferences.getInt(waterCurrentValue_daily, 00);
    }

    public void setWaterCurrentValue_daily(int waterCurrentValue) {
        mSharedPreferences.edit().putInt(waterCurrentValue_daily, waterCurrentValue).apply();
    }
}
