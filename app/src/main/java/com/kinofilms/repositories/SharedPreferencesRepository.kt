package com.kinofilms.repositories

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val GLOBAL_PREFERENCES = "global_preferences"

@Singleton
class SharedPreferencesRepository @Inject constructor(@ApplicationContext context: Context) {

    private val globalPreferences =
        context.getSharedPreferences(GLOBAL_PREFERENCES, Context.MODE_PRIVATE)

    fun setFavoriteMovie(key: String, value: Boolean) {
        globalPreferences.edit {
            putBoolean(key, value)
        }
    }

    fun getFavoriteMovie(key: String): Boolean {
        return globalPreferences.getBoolean(key, false)
    }
}