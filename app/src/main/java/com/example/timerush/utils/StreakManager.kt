package com.example.timerush.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

object StreakManager {

    private var lastPlayedDate: LocalDate? = null
    var streak = 0

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateStreak() {
        val today = LocalDate.now()

        if (lastPlayedDate == null || lastPlayedDate != today) {
            streak++
        }

        lastPlayedDate = today
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun resetIfMissedDay() {
        val today = LocalDate.now()
        if (lastPlayedDate != null && lastPlayedDate!!.isBefore(today.minusDays(1))) {
            streak = 0
        }
    }
}
