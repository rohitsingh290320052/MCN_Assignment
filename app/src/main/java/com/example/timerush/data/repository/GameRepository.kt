package com.example.timerush.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class GameRepository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun updatePoints(pointsEarned: Int) {
        val uid = auth.currentUser?.uid ?: return

        val userRef = firestore.collection("users").document(uid)

        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(userRef)
            val currentPoints = snapshot.getLong("totalPoints") ?: 0
            transaction.update(
                userRef,
                "totalPoints",
                currentPoints + pointsEarned
            )
        }
    }
}
