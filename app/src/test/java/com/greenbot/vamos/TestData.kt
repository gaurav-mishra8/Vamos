package com.greenbot.vamos

import com.greenbot.vamos.domain.model.Task

val task1 = Task(
    id = 1223456L,
    title = "Test Task 1",
    description = "Test Task Description 1",
    createdTime = System.currentTimeMillis(),
    updatedTime = System.currentTimeMillis(),
    targetCompletionTime = 1640320810000L
)

val task2 = Task(
    id = 1223457L,
    title = "Test Task 2",
    description = "Test Task Description 2",
    createdTime = System.currentTimeMillis(),
    updatedTime = System.currentTimeMillis(),
    targetCompletionTime = 1640320810000L
)

val taskList = listOf(task1, task2)