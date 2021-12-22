package com.greenbot.vamos.data

import com.greenbot.vamos.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskDataStore {
    fun getAllTasks(): Flow<List<Task>>
    fun saveTask(task: Task): Boolean
    fun getTask(taskId: Long): Task
    fun deleteTask(taskId: Long): Boolean
}