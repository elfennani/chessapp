package com.elfennani.chess.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elfennani.chess.presentation.screens.game.GameRoute
import com.elfennani.chess.presentation.screens.game.GameScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = GameRoute
    ) {
        composable<GameRoute> {
            GameScreen(navController)
        }
    }
}