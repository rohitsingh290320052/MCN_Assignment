package com.example.timerush.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.timerush.ui.dashboard.DashboardScreen
import com.example.timerush.ui.game.GameScreen
import com.example.timerush.ui.leaderboard.LeaderboardScreen
import com.example.timerush.viewmodel.AuthViewModel

@Composable
fun MainAppNav(authViewModel: AuthViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {

        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onLogout = { authViewModel.logout() },
                onPlay = { navController.navigate(Screen.Game.route) },
                onLeaderboard = { navController.navigate(Screen.Leaderboard.route) }
            )
        }

        composable(Screen.Game.route) {
            GameScreen(navController)
        }

        composable(Screen.Leaderboard.route) {
            LeaderboardScreen()
        }
    }
}
