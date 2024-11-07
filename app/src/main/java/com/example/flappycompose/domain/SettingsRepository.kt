package com.example.flappycompose.domain

import com.example.flappycompose.data.source.SharedPreferencesService
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val sp: SharedPreferencesService
) {
    fun getSelectedCharacter(): Characters {
        return when (sp.getCharacter()) {
            0 -> Characters.Yellow
            1 -> Characters.Blue
            2 -> Characters.Red
            else -> Characters.Yellow
        }
    }

    fun setCharacter(character: Characters) {
        val value = when (character) {
            Characters.Yellow -> 0
            Characters.Blue -> 1
            Characters.Red -> 2
        }
        sp.putCharacter(value)
    }
}