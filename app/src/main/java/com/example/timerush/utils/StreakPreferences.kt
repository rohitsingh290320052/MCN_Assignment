package com.example.timerush.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

private val Context.dataStore by preferencesDataStore(name = "streak_prefs")

object StreakPreferences {

    private val STREAK_KEY = intPreferencesKey("streak")
    private val LAST_PLAYED_KEY = stringPreferencesKey("last_played")

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun markQuizCompleted(context: Context) {
        val today = LocalDate.now().toString()

        context.dataStore.edit { prefs ->
            val lastPlayed = prefs[LAST_PLAYED_KEY]
            val currentStreak = prefs[STREAK_KEY] ?: 0

            if (lastPlayed == today) return@edit   // 🚫 prevent double increment

            val newStreak =
                if (lastPlayed == LocalDate.now().minusDays(1).toString())
                    currentStreak + 1
                else 1

            prefs[STREAK_KEY] = newStreak
            prefs[LAST_PLAYED_KEY] = today
        }
    }

    fun streakFlow(context: Context): Flow<Int> =
        context.dataStore.data.map { it[STREAK_KEY] ?: 0 }

    @RequiresApi(Build.VERSION_CODES.O)
    fun hasPlayedToday(context: Context): Flow<Boolean> =
        context.dataStore.data.map {
            it[LAST_PLAYED_KEY] == LocalDate.now().toString()
        }
}
