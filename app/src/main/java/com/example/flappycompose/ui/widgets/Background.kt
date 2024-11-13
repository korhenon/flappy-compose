package com.example.flappycompose.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.flappycompose.R

@Composable
fun Background() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Image(
            painter = painterResource(id = R.drawable.floor),
            contentDescription = "",
            Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}