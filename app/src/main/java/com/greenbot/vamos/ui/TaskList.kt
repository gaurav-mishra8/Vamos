package com.greenbot.vamos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaskList() {
    Column {
        Text("Vamos")
        Text("My Task List")
    }
}

@Preview
@Composable
fun Preview() {
    TaskList()
}