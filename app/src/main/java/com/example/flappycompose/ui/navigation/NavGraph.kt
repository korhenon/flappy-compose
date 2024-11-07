package com.example.flappycompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flappycompose.ui.navigation.destinations.CharacterSelect
import com.example.flappycompose.ui.navigation.destinations.Game
import com.example.flappycompose.ui.navigation.destinations.Home
import com.example.flappycompose.ui.screen.character_select.CharacterSelectScreen
import com.example.flappycompose.ui.screen.game.GameScreen
import com.example.flappycompose.ui.screen.home.HomeScreen

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home, modifier = modifier) {
        composable<Home> { HomeScreen(navController = navController) }
        composable<CharacterSelect> { CharacterSelectScreen(navController = navController) }
        composable<Game> { GameScreen() }
    }
}