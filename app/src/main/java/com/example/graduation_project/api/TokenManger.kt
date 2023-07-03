package com.example.graduation_project.api

import android.content.Context
import com.example.graduation_project.util.Constants.Companion.PREFS_TOKEN_FILE
import com.example.graduation_project.util.Constants.Companion.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context

) {

    private var sharedPreferences =
        context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)


    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(USER_TOKEN, null)
    }
}