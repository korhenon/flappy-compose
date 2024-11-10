package com.example.flappycompose.ui.screen.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flappycompose.common.Constants

@Composable
fun GameScreen(viewModel: GameViewModel = hiltViewModel()) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        if (viewModel.frame == null && viewModel.startGame) {
            viewModel.startLoop(size.width, size.height)
        }
        if (viewModel.frame != null) {
            val frame = viewModel.frame!!
            drawCircle(
                Color.Red,
                (Constants.BIRD_SIZE / 2).toFloat(),
                Offset(frame.bird.x.toFloat(), frame.bird.y.toFloat())
            )
            for (tube in frame.tubes) {
                drawRect(
                    Color.Green,
                    topLeft = Offset((tube.x - Constants.TUBE_WIDTH / 2).toFloat(), 0f),
                    size = Size(Constants.TUBE_WIDTH.toFloat(), tube.spaceStartY.toFloat())
                )
                drawRect(
                    Color.Green,
                    topLeft = Offset(
                        (tube.x - Constants.TUBE_WIDTH / 2).toFloat(),
                        (tube.spaceStartY + Constants.SPACE_HEIGHT).toFloat()
                    ),
                    size = Size(Constants.TUBE_WIDTH.toFloat(), size.height - (tube.spaceStartY + Constants.SPACE_HEIGHT))
                )
            }
        }
    }
}