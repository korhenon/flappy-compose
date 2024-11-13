package com.example.flappycompose.ui.screen.game

import android.media.MediaPlayer
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flappycompose.R
import com.example.flappycompose.common.Constants
import com.example.flappycompose.domain.Characters
import com.example.flappycompose.ui.navigation.destinations.Home
import com.example.flappycompose.ui.widgets.Background

@Composable
fun GameScreen(navController: NavController, viewModel: GameViewModel = hiltViewModel()) {
    val interactionSource = remember { MutableInteractionSource() }
    val birdBitmap = ImageBitmap.imageResource(
        id = when (viewModel.selectedCharacter) {
            Characters.Yellow -> R.drawable.yellow_up
            Characters.Blue -> R.drawable.blue_up
            Characters.Red -> R.drawable.red_up
        }
    )
    val topTubeBitmap = ImageBitmap.imageResource(id = R.drawable.tube_top)
    val bottomTubeBitmap = ImageBitmap.imageResource(id = R.drawable.tube_bottom)

    Background()
    Canvas(modifier = Modifier
        .fillMaxSize()
        .clickable(interactionSource = interactionSource, indication = null) {
            viewModel.jump()
        }) {
        if (viewModel.frame == null && viewModel.startGame) {
            viewModel.startLoop(size.width, size.height)
        }
        if (viewModel.frame != null) {
            val frame = viewModel.frame!!
            drawImage(
                birdBitmap, dstOffset = IntOffset(
                    (frame.bird.x - Constants.BIRD_WIDTH / 2).toInt(),
                    (frame.bird.y - Constants.BIRD_HEIGHT / 2).toInt()
                ),
                dstSize = IntSize(Constants.BIRD_WIDTH.toInt(), Constants.BIRD_HEIGHT.toInt())
            )
            for (tube in frame.tubes) {
                drawImage(
                    topTubeBitmap, dstOffset = IntOffset(
                        (tube.x - Constants.TUBE_WIDTH / 2).toInt(),
                        (-Constants.TUBE_HEIGHT + tube.spaceStartY).toInt()
                    ),
                    dstSize = IntSize(Constants.TUBE_WIDTH.toInt(), Constants.TUBE_HEIGHT.toInt())
                )
                drawImage(
                    bottomTubeBitmap, dstOffset = IntOffset(
                        (tube.x - Constants.TUBE_WIDTH / 2).toInt(),
                        (tube.spaceStartY + Constants.SPACE_HEIGHT).toInt()
                    ),
                    dstSize = IntSize(Constants.TUBE_WIDTH.toInt(), Constants.TUBE_HEIGHT.toInt())
                )

            }
        }
    }
    Text(
        text = "Результат: ${viewModel.score}\nУровень: ${viewModel.score / 20}",
        modifier = Modifier.padding(20.dp),
        fontWeight = FontWeight.Bold
    )
    if (viewModel.frame == null && !viewModel.startGame && !viewModel.isWin) {
        AlertDialog(
            onDismissRequest = { navController.navigate(Home) },
            confirmButton = {
                Text(
                    text = "Начать заново",
                    Modifier.clickable { viewModel.startGame = true })
            },
            dismissButton = {
                Text(
                    text = "В меню",
                    Modifier.clickable { navController.navigate(Home) })
            },
            title = { Text(text = "Вы проиграли") },
            text = { Text(text = "Ваш результат: ${viewModel.score}") }
        )
    }
    if (viewModel.frame == null &&viewModel.isWin) {
        AlertDialog(
            onDismissRequest = { navController.navigate(Home) },
            confirmButton = {
                Text(
                    text = "Начать заново",
                    Modifier.clickable { viewModel.startGame = true })
            },
            dismissButton = {
                Text(
                    text = "В меню",
                    Modifier.clickable { navController.navigate(Home) })
            },
            title = { Text(text = "Вы выиграли") },
            text = { Text(text = "Поздравляю понимаю насколько это было сложно...") }
        )
    }
}