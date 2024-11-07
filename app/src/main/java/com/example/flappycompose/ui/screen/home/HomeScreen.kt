package com.example.flappycompose.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flappycompose.ui.navigation.destinations.CharacterSelect
import com.example.flappycompose.ui.navigation.destinations.Game
import com.example.flappycompose.ui.widgets.MenuBackground

@Composable
fun HomeScreen(navController: NavController) {
    MenuBackground()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Flappy Compose", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = { navController.navigate(Game) }) {
                Text(text = "Начать игру")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { navController.navigate(CharacterSelect) }) {
                Text(text = "Изменить персонажа")
            }
        }
    }
}