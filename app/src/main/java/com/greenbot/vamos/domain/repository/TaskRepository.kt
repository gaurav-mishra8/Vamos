package com.greenbot.vamos.domain.repository

import com.greenbot.vamos.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTasks(): Flow<List<Task>>

    fun saveTask(task: Task): Boolean

    fun getTask(taskId: Long): Task

    fun deleteTask(taskId: Long): Boolean
}