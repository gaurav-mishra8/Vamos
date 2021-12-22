package com.greenbot.vamos.data.local

import com.greenbot.vamos.domain.model.Task

interface TaskCache {

    fun getAllTasks(): List<Task>

    fun getTaskById(taskId: Long): Task

    fun saveTask(task: Task): Boolean

    fun deleteTask(taskId: Long): Boolean
}