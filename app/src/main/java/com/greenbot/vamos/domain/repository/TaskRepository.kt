package com.greenbot.vamos.domain.repository

import com.greenbot.vamos.domain.model.Task

interface TaskRepository {

    fun getAllTasks(): List<Task>

    fun saveTask(task: Task): Boolean

    fun getTaskById(taskId: Long): Task?

    fun deleteTask(taskId: Long): Boolean
}