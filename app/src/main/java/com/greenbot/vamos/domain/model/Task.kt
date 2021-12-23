package com.greenbot.vamos.domain.model

data class Task(
    val id: Long,
    val title: String = "",
    val description: String = "",
    val priority: Int = 0,
    val isCompleted: Boolean = false,
    val createdTime: Long,
    val updatedTime: Long,
    val targetCompletionTime: Long
)
