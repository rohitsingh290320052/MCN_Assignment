package com.example.timerush.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

data class LeaderboardUser(
    val name: String,
    val score: Int,
    val rank: Int
)

class LeaderboardViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    val leaderboard = mutableStateOf<List<LeaderboardUser>>(emptyList())
    val isLoading = mutableStateOf(true)

    init {
        db.collection("users")
            .orderBy("totalPoints", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) {
                    return@addSnapshotListener
                }

                val users = snapshot.documents.mapNotNull { doc ->
                    val name = doc.getString("name")
                    val score = doc.getLong("totalPoints")?.toInt()

                    if (name != null && score != null) {
                        Pair(name, score)
                    } else null
                }

                leaderboard.value = assignRanks(users)
                isLoading.value = false
            }
    }

    private fun assignRanks(users: List<Pair<String, Int>>): List<LeaderboardUser> {
        var currentRank = 1
        var lastScore: Int? = null

        return users.mapIndexed { index, (name, score) ->
            if (lastScore != null && score < lastScore!!) {
                currentRank = index + 1
            }
            lastScore = score
            LeaderboardUser(name, score, currentRank)
        }
    }
}
