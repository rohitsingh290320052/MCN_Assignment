package com.example.timerush.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.timerush.ui.auth.LoginScreen
import com.example.timerush.ui.dashboard.DashboardScreen
import com.example.timerush.ui.game.GameScreen
import com.example.timerush.ui.leaderboard.LeaderboardScreen
import com.example.timerush.viewmodel.AuthViewModel


@Composable
fun AppNavGraph() {

    val authViewModel = remember { AuthViewModel() }

    if (authViewModel.isLoggedIn.value) {
        MainAppNav(authViewModel)
    } else {
        LoginScreen(viewModel = authViewModel)
    }
}

