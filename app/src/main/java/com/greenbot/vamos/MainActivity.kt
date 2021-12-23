package com.greenbot.vamos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.greenbot.vamos.ui.TaskList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(topBar = {
                AppBar()
            }) {
                TaskList()
            }
        }
    }

    @Composable
    private fun AppBar() {
        Surface(color = Color.Green, modifier = Modifier.fillMaxWidth()) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "VAMOS")
            }
        }
    }
}