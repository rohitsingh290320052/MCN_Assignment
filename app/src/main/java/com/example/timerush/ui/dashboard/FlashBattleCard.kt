package com.example.timerush.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.timerush.viewmodel.FlashBattleViewModel

@Composable
fun FlashBattleCard(
    viewModel: FlashBattleViewModel,
    onJoin: () -> Unit
) {
    if (!viewModel.isActive) return

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("⚡ Flash Battle Live!", style = MaterialTheme.typography.titleMedium)
            Text("Ends in ${viewModel.remainingTime / 1000}s")

            Spacer(Modifier.height(8.dp))

            Button(onClick = onJoin) {
                Text("Join Now")
            }
        }
    }
}
