package com.example.timerush.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ParticipationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val score: Int,
    val timeSpent: Long
)
