package com.example.timerush.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.timerush.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity WHERE id = 1")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)
}
