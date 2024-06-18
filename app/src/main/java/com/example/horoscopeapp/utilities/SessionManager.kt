package com.example.horoscopeapp.utilities

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    companion object{
        const val FAVORITE_HOROSCOPE: String = "FAVORITE_HOROSCOPE"
    }

    //val sharedPref: SharedPreferences = context.getSharedPreferences("horoscope_session", Context.MODE_PRIVATE)

    // use init to initialize the Shared references optional:

    val sharedPref: SharedPreferences
    init {
    sharedPref = context.getSharedPreferences("horoscope_session", Context.MODE_PRIVATE)
    }

    fun setHoroscopeToFavorite(id: String?) {
        sharedPref.edit().putString(FAVORITE_HOROSCOPE, id).apply()
    }

    fun setHoroscopeFavoriteToNull() {
        sharedPref.edit().putString(FAVORITE_HOROSCOPE, null).apply()
    }

    fun getFavoriteHoroscope() : String? {
        return sharedPref.getString(FAVORITE_HOROSCOPE, null)
    }

    fun isFavorite(id: String) : Boolean {
        return sharedPref.getString(FAVORITE_HOROSCOPE, null).equals(id)
    }
}

