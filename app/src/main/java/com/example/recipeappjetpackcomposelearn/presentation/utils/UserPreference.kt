package com.example.recipeappjetpackcomposelearn.presentation.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.recipeappjetpackcomposelearn.R

object UserPreference {

    private lateinit var prefs: SharedPreferences

    fun initPrefs(context: Context){
        prefs = context.getSharedPreferences(
            R.string.preference_file_key.toString(),
            Context.MODE_PRIVATE
        )
    }

    fun changeThemeMode(){
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putBoolean("dark_mode", !prefs.getBoolean("dark_mode", false))
            commit()
        }
    }

    fun readThemeMode(): Boolean{
        return prefs.getBoolean("dark_mode", false)
    }

}