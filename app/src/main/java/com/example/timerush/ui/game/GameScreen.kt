package com.example.timerush.ui.game

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.timerush.data.local.AppDatabase
import com.example.timerush.data.repository.GameRepository
import com.example.timerush.navigation.Screen
import com.example.timerush.viewmodel.GameViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameScreen(navController: NavController) {

    val context = LocalContext.current
    val viewModel = remember { GameViewModel(GameRepository()) }
    val question = viewModel.currentQuestion

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Card {
            Column(Modifier.padding(16.dp)) {

                if (!viewModel.isGameFinished) {
                    Text(
                        question.question,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(Modifier.height(12.dp))

                    question.options.forEach { option ->
                        OutlinedButton(
                            onClick = { viewModel.submitAnswer(option) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(option)
                        }
                    }
                } else {
                    Text(
                        "🎉 Game Finished!",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }

        Card {
            Column(Modifier.padding(16.dp)) {
                AnimatedScore(score = viewModel.score)
            }
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {
                viewModel.finishGame()
                navController.navigate(Screen.Dashboard.route) {
                    popUpTo(Screen.Dashboard.route) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Finish")
        }
    }
}


@Composable
fun AnimatedScore(score: Int) {
    AnimatedContent(
        targetState = score,
        label = "ScoreAnimation"
    ) { value: Int ->
        Text(
            text = "Score: $value",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
