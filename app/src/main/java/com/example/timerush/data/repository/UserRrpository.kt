package com.example.timerush.data.repository

import com.example.timerush.data.local.dao.UserDao
import com.example.timerush.data.local.entity.UserEntity

class UserRepository(private val dao: UserDao) {

    suspend fun addPoints(points: Int) {
        val user = dao.getUser() ?: UserEntity(totalPoints = 0)
        dao.insert(
            user.copy(totalPoints = user.totalPoints + points)
        )
    }
}


