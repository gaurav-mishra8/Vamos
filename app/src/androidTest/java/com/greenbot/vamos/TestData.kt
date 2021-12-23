package com.greenbot.vamos

import com.greenbot.vamos.data.local.db.model.TaskEntity

val testTaskEntity1 = TaskEntity(
    id = 12323234234L,
    title = "My First Test Task",
    description = "This is a test task",
    isCompleted = false,
    priority = 0,
    createdTime = System.currentTimeMillis(),
    updatedTime = System.currentTimeMillis(),
    targetCompletionTime = 1640320810000L
)

val testTaskEntity2 = TaskEntity(
    id = 12323234235L,
    title = "My Second Test Task",
    description = "This is a test task",
    isCompleted = true,
    priority = 2,
    createdTime = System.currentTimeMillis(),
    updatedTime = System.currentTimeMillis(),
    targetCompletionTime = 1640320810000L
)