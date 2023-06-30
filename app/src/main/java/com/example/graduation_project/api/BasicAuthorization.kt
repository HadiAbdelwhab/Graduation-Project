package com.example.graduation_project.api

import android.util.Base64

class BasicAuthorization(private val userName: String, private val password: String) {
    private val credentials: String = "$userName:$password"

    fun getCredentials(): String {
        val encodedCredentials: ByteArray = Base64.encode(credentials.toByteArray(), Base64.NO_WRAP)
        return "Basic " + String(encodedCredentials)
    }
}

/*public class BasicAuthorization {
    private String credentials;

    public BasicAuthorization(String userName, String password) {
        this.credentials =  userName + ":" + password;;
    }

    public String getCredentials() {
        return "Basic "+Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    }
}*/