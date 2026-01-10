package com.example.timerush.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
    val flashViewModel = remember { FlashBattleViewModel() }

    LaunchedEffect(Unit) {
        flashViewModel.startBattle()
    }


    Column(modifier = Modifier.padding(16.dp)) {

        Text("Daily League", style = MaterialTheme.typography.headlineMedium)
        Text("Streak: ${StreakManager.streak} 🔥")

        Spacer(Modifier.height(16.dp))

        Button(onClick = onPlay) {
            Text("Play Challenge")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = onLeaderboard) {
            Text("View Leaderboard")
        }

        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }

    }
}
