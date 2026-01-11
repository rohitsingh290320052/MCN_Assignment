package com.example.timerush.ui.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.timerush.utils.StreakManager
import com.example.timerush.utils.StreakPreferences
import com.example.timerush.viewmodel.DashboardViewModel
import com.example.timerush.viewmodel.FlashBattleViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen(
    onPlay: () -> Unit,
    onLeaderboard: () -> Unit,
    onLogout: () -> Unit,
    viewModel: DashboardViewModel = viewModel()
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
                Text("Streak: ${viewModel.streak.value} days")
            }
        }

        Card {
            Column(Modifier.padding(16.dp)) {

                if (!viewModel.hasPlayedToday.value) {
                    Button(
                        onClick = onPlay,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Play Challenge")
                    }
                } else {
                    Text(
                        "✅ Challenge completed for today",
                        style = MaterialTheme.typography.bodyMedium
                    )
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


