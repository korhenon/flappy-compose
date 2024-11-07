package com.example.flappycompose.data.source

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sp get() = context.getSharedPreferences("FC_SP", Context.MODE_PRIVATE)
    private val editor get() = sp.edit()

    fun getCharacter(): Int {
        return sp.getInt("CHARACTER", 0)
    }

    fun putCharacter(value: Int) {
        editor.putInt("CHARACTER", value).apply()
    }
}