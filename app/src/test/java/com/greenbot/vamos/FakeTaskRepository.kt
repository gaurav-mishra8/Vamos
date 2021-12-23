package com.greenbot.vamos

import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.repository.TaskRepository
import com.greenbot.vamos.domain.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTaskRepository : TaskRepository {

    override fun getAllTasks(): Flow<Result<List<Task>>> = flow {
        emit(Result.Loading)
        emit(Result.Success(taskList))
    }

    override fun saveTask(task: Task): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun getTaskById(taskId: Long): Flow<Result<Task?>> = flow {
        emit(Result.Loading)
        val task = taskList.find { it.id == taskId }
        emit(Result.Success(task))
    }

    override fun deleteTask(taskId: Long): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }
}