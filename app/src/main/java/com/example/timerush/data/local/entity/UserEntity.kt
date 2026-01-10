package com.example.timerush.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val id: Int = 1,
    val totalPoints: Int
)