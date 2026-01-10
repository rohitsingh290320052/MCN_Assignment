package com.example.timerush.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

data class LeaderboardUser(
    val name: String = "",
    val score: Int = 0
)

class LeaderboardViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    var leaderboard = mutableStateOf<List<LeaderboardUser>>(emptyList())
        private set

    init {
        db.collection("users")
            .orderBy("totalPoints", Query.Direction.DESCENDING)
            .limit(20)
            .addSnapshotListener { snapshot, error ->
                if (snapshot != null) {
                    leaderboard.value = snapshot.documents.mapNotNull { doc ->
                        val name = doc.getString("name") ?: "User"
                        val score = doc.getLong("totalPoints")?.toInt() ?: 0
                        LeaderboardUser(name, score)
                    }
                }
            }
    }
}
