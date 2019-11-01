package com.nithinkumar.water

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by nithinkumar on 7/21/17.
 */

class WaterShared(context: Context) {

    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    var name: String
        get() = sharedPreferences.getString(NAME_USER_VALUE, "UserName") ?: "UserName"
        set(value) = sharedPreferences.edit().putString(NAME_USER_VALUE, value).apply()

    var height: Int
        get() = sharedPreferences.getInt(HEIGHT_USER_VALUE, 0)
        set(value) = sharedPreferences.edit().putInt(HEIGHT_USER_VALUE, value).apply()

    var weight: Int
        get() = sharedPreferences.getInt(WEIGHT_USER_VALUE, 0)
        set(value) = sharedPreferences.edit().putInt(WEIGHT_USER_VALUE, value).apply()

    var startTime: String
        get() = sharedPreferences.getString(START_TIME_USER_VALUE, "") ?: ""
        set(value) = sharedPreferences.edit().putString(START_TIME_USER_VALUE, value).apply()

    var endTime: String
        get() = sharedPreferences.getString(END_TIME_USER_VALUE, "") ?: ""
        set(value) = sharedPreferences.edit().putString(END_TIME_USER_VALUE, value).apply()

    var firstRun: String
        get() = sharedPreferences.getString(FIRST_RUN_APP, "") ?: ""
        set(value) = sharedPreferences.edit().putString(FIRST_RUN_APP, value).apply()

    var waterAddedCurrent: Int
        get() = sharedPreferences.getInt(WATER_ADDED_CURRENT, 0)
        set(value) = sharedPreferences.edit().putInt(WATER_ADDED_CURRENT, value).apply()

    var waterAddedDaily: Int
        get() = sharedPreferences.getInt(WATER_ADDED_DAILY, 0)
        set(value) = sharedPreferences.edit().putInt(WATER_ADDED_DAILY, value).apply()

    var seenOnboarding: Boolean
        get() = sharedPreferences.getBoolean(SEEN_ONBOARDING, false)
        set(value) = sharedPreferences.edit().putBoolean(SEEN_ONBOARDING, value).apply()

    companion object {
        private const val NAME_USER_VALUE = "NAME_USER_VALUE"
        private const val HEIGHT_USER_VALUE = "HEIGHT_USER_VALUE"
        private const val WEIGHT_USER_VALUE = "WEIGHT_USER_VALUE"
        private const val START_TIME_USER_VALUE = "START_TIME_USER_VALUE"
        private const val END_TIME_USER_VALUE = "END_TIME_USER_VALUE"
        private const val FIRST_RUN_APP = "FIRST_RUN_APP"
        private const val WATER_ADDED_CURRENT = "WATER_ADDED_CURRENT"
        private const val WATER_ADDED_DAILY = "WATER_ADDED_DAILY"
        private const val SEEN_ONBOARDING = "SEEN_ONBOARDING"
    }
}
