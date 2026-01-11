//package com.example.timerush.data.repository
//
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//
//class GameRepository {
//
//    private val auth = FirebaseAuth.getInstance()
//    private val firestore = FirebaseFirestore.getInstance()
//
//    fun updatePoints(pointsEarned: Int) {
//        val uid = auth.currentUser?.uid ?: return
//
//        val userRef = firestore.collection("users").document(uid)
//
//        firestore.runTransaction { transaction ->
//            val snapshot = transaction.get(userRef)
//            val currentPoints = snapshot.getLong("totalPoints") ?: 0
//            transaction.update(
//                userRef,
//                "totalPoints",
//                currentPoints + pointsEarned
//            )
//        }
//    }
//}


package com.example.timerush.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate

class GameRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    fun completeQuiz(points: Int) {
        val uid = auth.currentUser?.uid ?: return
        val today = LocalDate.now().toString()

        val userRef = db.collection("users").document(uid)

        db.runTransaction { transaction ->
            val snapshot = transaction.get(userRef)

            val lastPlayed = snapshot.getString("lastPlayedDate")
            val currentStreak = snapshot.getLong("streak") ?: 0
            val totalPoints = snapshot.getLong("totalPoints") ?: 0

            val newStreak =
                if (lastPlayed == today) currentStreak
                else if (lastPlayed == LocalDate.now().minusDays(1).toString())
                    currentStreak + 1
                else 1

            transaction.update(
                userRef,
                mapOf(
                    "streak" to newStreak,
                    "lastPlayedDate" to today,
                    "totalPoints" to totalPoints + points
                )
            )
        }
    }
}
