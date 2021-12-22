package com.greenbot.vamos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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