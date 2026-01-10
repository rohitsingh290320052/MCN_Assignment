package com.example.timerush.ui.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.timerush.utils.LeaderboardData
import com.example.timerush.viewmodel.LeaderboardViewModel

@Composable
fun LeaderboardScreen(viewModel: LeaderboardViewModel = LeaderboardViewModel()) {

    Column(Modifier.padding(16.dp)) {

        Text(
            "Global Leaderboard 🌍",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(12.dp))

        viewModel.leaderboard.value.forEachIndexed { index, user ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Row(
                    Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${index + 1}. ${user.name}")
                    Text("${user.score} pts")
                }
            }
        }
    }
}
