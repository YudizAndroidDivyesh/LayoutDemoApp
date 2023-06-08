package com.example.demooflayouts.koinDI

import android.content.Context

private const val PREFS_NAME = "UtilShared"
private const val KEY_NAME = "Koin DI"
class MyUtil(context: Context)  {

        fun saveName(context: Context, name: String) {
            val editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
            editor.putString(KEY_NAME, name)
            editor.apply()
        }

        fun getName(context: Context): String? {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return prefs.getString(KEY_NAME, null)
        }


}