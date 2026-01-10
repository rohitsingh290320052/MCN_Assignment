package com.example.timerush.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.timerush.utils.StreakManager
import com.example.timerush.viewmodel.FlashBattleViewModel

@Composable
fun DashboardScreen(
    onPlay: () -> Unit,
    onLeaderboard: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Card {
            Column(Modifier.padding(16.dp)) {
                Text("🔥 Daily League", style = MaterialTheme.typography.headlineMedium)
                Text(
                    "Streak: ${StreakManager.streak}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Card {
            Column(Modifier.padding(16.dp)) {
                Button(
                    onClick = onPlay,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Play Challenge")
                }

                Spacer(Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onLeaderboard,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("View Leaderboard")
                }
            }
        }

        Spacer(Modifier.weight(1f))

        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}
