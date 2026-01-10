package com.example.timerush.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameEntity(
    @PrimaryKey(autoGenerate = true) val gameId: Int,
    val question: String,
    val correctAnswer: String
)
