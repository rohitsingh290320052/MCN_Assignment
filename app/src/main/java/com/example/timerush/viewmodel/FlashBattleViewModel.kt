package com.example.timerush.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class FlashBattleViewModel : ViewModel() {

    var remainingTime by mutableStateOf(0L)
        private set

    var isActive by mutableStateOf(false)
        private set

    fun startBattle(durationMillis: Long = 10 * 60 * 1000) {
        isActive = true
        remainingTime = durationMillis

        viewModelScope.launch {
            while (remainingTime > 0) {
                delay(1000)
                remainingTime -= 1000
            }
            isActive = false
        }
    }
}
