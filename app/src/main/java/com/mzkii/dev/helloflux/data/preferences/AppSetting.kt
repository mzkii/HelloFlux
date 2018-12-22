package com.mzkii.dev.githubgistclient.data.preferences

import android.content.SharedPreferences

class AppSetting(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val PREF_ACCESS_TOKEN = "access_token"
    }

    var accessToken: String
        get() = "token ${sharedPreferences.getString(PREF_ACCESS_TOKEN, "")}"
        set(value) = sharedPreferences.edit().putString(PREF_ACCESS_TOKEN, value).apply()
}