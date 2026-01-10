package com.example.timerush.utils

object RewardCalculator {

    fun calculateReward(
        basePoints: Int,
        streak: Int,
        difficulty: Int
    ): Int {
        return basePoints * streak * difficulty
    }
}
