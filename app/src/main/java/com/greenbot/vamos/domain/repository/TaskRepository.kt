package com.greenbot.vamos.domain.repository

import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTasks(): Flow<Result<List<Task>>>

    fun saveTask(task: Task): Flow<Result<Boolean>>

    fun getTaskById(taskId: Long): Flow<Result<Task?>>

    fun deleteTask(taskId: Long): Flow<Result<Boolean>>
}