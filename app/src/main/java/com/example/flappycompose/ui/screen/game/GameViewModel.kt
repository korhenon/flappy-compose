package com.example.flappycompose.ui.screen.game

import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flappycompose.data.models.Frame
import com.example.flappycompose.domain.Characters
import com.example.flappycompose.domain.GameRepository
import com.example.flappycompose.domain.SettingsRepository
import com.example.flappycompose.ui.utils.SoundController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository,
    settingsRepository: SettingsRepository,
    private val soundController: SoundController
) : ViewModel() {
    var frame by mutableStateOf<Frame?>(null)
    var startGame by mutableStateOf(true)
    var score by mutableIntStateOf(0)
    var isWin by mutableStateOf(false)
    var selectedCharacter by mutableStateOf(Characters.Yellow)

    init {
        selectedCharacter = settingsRepository.getSelectedCharacter()
    }

    fun startLoop(width: Float, height: Float) {
        isWin = false
        soundController.startTheme()
        startGame = false
        score = 0
        repository.setSize(width, height)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isWin = repository.loop({ frame = it }, {
                    score = it
                    if (it.mod(20) == 0) soundController.playLevelUp()
                })
                soundController.stopTheme()
            }
        }
    }

    fun jump() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.jump()
            }
        }
    }
}