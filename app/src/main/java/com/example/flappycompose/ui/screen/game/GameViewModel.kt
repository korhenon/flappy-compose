package com.example.flappycompose.ui.screen.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flappycompose.data.models.Frame
import com.example.flappycompose.domain.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    val repository: GameRepository
) : ViewModel() {
    var frame by mutableStateOf<Frame?>(null)
    var startGame by mutableStateOf(true)

    fun startLoop(width: Float, height: Float) {
        startGame = false
        repository.setSize(width, height)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.loop { frame = it }
            }
        }
    }
}