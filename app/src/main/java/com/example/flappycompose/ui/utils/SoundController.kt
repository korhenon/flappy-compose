package com.example.flappycompose.ui.utils

import android.content.Context
import android.media.MediaPlayer
import com.example.flappycompose.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SoundController @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var theme = MediaPlayer.create(context, R.raw.theme)
    private var levelUp = MediaPlayer.create(context, R.raw.level_up)

    fun startTheme() {
        theme.isLooping = true
        theme.seekTo(0)
        theme.start()
    }

    fun stopTheme() {
        theme.pause()
    }

    fun playLevelUp() {
        levelUp.seekTo(0)
        levelUp.start()
    }
}