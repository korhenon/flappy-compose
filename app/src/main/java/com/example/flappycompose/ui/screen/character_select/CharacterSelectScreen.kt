package com.example.flappycompose.ui.screen.character_select

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flappycompose.R
import com.example.flappycompose.domain.Characters
import com.example.flappycompose.ui.widgets.Background

@Composable
fun CharacterSelectScreen(
    navController: NavController,
    viewModel: CharacterSelectViewModel = hiltViewModel()
) {
    Background()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Выберите персонажа",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 42.sp
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (viewModel.selectedCharacter == Characters.Yellow) colorScheme.primary
                        else Color.Transparent
                    ),
                    onClick = {
                        viewModel.setYellow()
                        navController.popBackStack()
                    },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.yellow_up),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                    )
                }
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (viewModel.selectedCharacter == Characters.Blue) colorScheme.primary
                        else Color.Transparent
                    ),
                    onClick = {
                        viewModel.setBlue()
                        navController.popBackStack()
                    },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.blue_up),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                    )
                }
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (viewModel.selectedCharacter == Characters.Red) colorScheme.primary
                        else Color.Transparent
                    ),
                    onClick = {
                        viewModel.setRed()
                        navController.popBackStack()
                    },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.red_up),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp),
                    )
                }
            }
        }
    }
}