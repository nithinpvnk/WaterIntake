package com.nithinkumar.water.acceptance

import android.content.Context
import android.preference.PreferenceManager

object AndroidTestHelpers {
    fun clearSharedPreferences(context: Context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
                clear()
                commit()
        }
    }
}
