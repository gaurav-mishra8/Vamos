package com.greenbot.vamos.data

import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.repository.TaskRepository
import com.greenbot.vamos.domain.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class TaskRepositoryImpl @Inject constructor(
    @Named("local") private val localDataStore: TaskDataStore,
    @Named("remote") private val remoteDataStore: TaskDataStore
) : TaskRepository {

    override fun getAllTasks(): Flow<Result<List<Task>>> {
        return flow {
            emit(Result.Loading)

            val tasks = localDataStore.getAllTasks()
            emit(Result.Success(tasks))
        }
    }

    override fun saveTask(task: Task): Flow<Result<Boolean>> {
        return flow {
            emit(Result.Loading)

            localDataStore.saveTask(task)
            emit(Result.Success(true))
        }
    }

    override fun getTaskById(taskId: Long): Flow<Result<Task?>> {
        return flow {
            emit(Result.Loading)

            val task = localDataStore.getTask(taskId)
            emit(Result.Success(task))
        }
    }

    override fun deleteTask(taskId: Long): Flow<Result<Boolean>> {
        return flow {
            emit(Result.Loading)
            localDataStore.deleteTask(taskId)
            emit(Result.Success(true))
        }
    }
}