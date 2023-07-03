package com.example.graduation_project.util

import androidx.datastore.preferences.core.stringPreferencesKey

class Constants {
    companion object {
        const val BASE_URL="http://10.0.2.2:8000/"
        const val SHA_PRF_KEY="key"
        const val TOKEN_KEY="token"
        const val USER_TOKEN="user_token"
        const val PREFS_TOKEN_FILE="PREFS_TOKEN_FILE"
        //private val TOKEN_KEY = stringPreferencesKey("jwt_token")

    }
}