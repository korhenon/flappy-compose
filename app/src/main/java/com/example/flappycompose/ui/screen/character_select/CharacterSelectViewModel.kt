package com.example.flappycompose.ui.screen.character_select

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.flappycompose.domain.Characters
import com.example.flappycompose.domain.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterSelectViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {
    var selectedCharacter by mutableStateOf(Characters.Yellow)

    init {
        selectedCharacter = repository.getSelectedCharacter()
    }

    fun setYellow() {
        repository.setCharacter(Characters.Yellow)
    }

    fun setBlue() {
        repository.setCharacter(Characters.Blue)
    }

    fun setRed() {
        repository.setCharacter(Characters.Red)
    }
}