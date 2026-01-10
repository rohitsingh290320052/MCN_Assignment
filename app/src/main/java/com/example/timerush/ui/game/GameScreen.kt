package com.example.timerush.ui.game

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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

@Composable
fun GameScreen(navController: NavController) {

    // ✅ Context
    val context = LocalContext.current

    // ✅ Database (temporary MVP setup)
    val database = remember {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "timerush_db"
        ).build()
    }

    // ✅ Repository
    val repository = remember {
        GameRepository(database.userDao())
    }

    // ✅ ViewModel with constructor injection
    val viewModel = remember {
        GameViewModel(repository)
    }

    val question = viewModel.currentQuestion

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // ✅ QUIZ CONTENT
        if (!viewModel.isGameFinished) {

            Text(
                text = question.question,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            val options: List<String> = question.options
            for (option in options) {
                Button(
                    onClick = { viewModel.submitAnswer(option) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(option)
                }
            }

        } else {

            Text(
                text = "Game Finished 🎉",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ SCORE (always visible)
        AnimatedScore(score = viewModel.score)

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ FINISH BUTTON
        Button(
            onClick = {
                viewModel.finishGame(context)
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
