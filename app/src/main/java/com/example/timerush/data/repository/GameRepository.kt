package com.example.timerush.data.repository
import com.example.timerush.data.local.dao.UserDao
import com.example.timerush.data.local.entity.UserEntity

class GameRepository(
    private val userDao: UserDao
) {

    suspend fun updateRewards(points: Int) {
        val user = userDao.getUser() ?: UserEntity(totalPoints = 0)

        userDao.insert(
            user.copy(
                totalPoints = user.totalPoints + points
            )
        )
    }
}

