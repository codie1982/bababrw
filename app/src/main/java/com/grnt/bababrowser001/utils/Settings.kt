package com.grnt.bababrowser001.utils

import android.content.Context
import android.content.SharedPreferences

class Settings(private val appContext: Context) : PreferencesHolder {
    companion object{
        const val FENIX_PREFERENCES = "fenix_preferences"
    }
    override val preferences: SharedPreferences =
        appContext.getSharedPreferences(FENIX_PREFERENCES, Context.MODE_PRIVATE)
}


interface PreferencesHolder {
    val preferences: SharedPreferences
}
