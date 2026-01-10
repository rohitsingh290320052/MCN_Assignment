package com.example.timerush.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.timerush.data.local.dao.UserDao
import com.example.timerush.data.local.entity.GameEntity
import com.example.timerush.data.local.entity.ParticipationEntity
import com.example.timerush.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, GameEntity::class, ParticipationEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
