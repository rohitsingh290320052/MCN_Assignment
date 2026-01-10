package com.example.timerush.navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Game : Screen("game")
    object Leaderboard : Screen("leaderboard")
}
