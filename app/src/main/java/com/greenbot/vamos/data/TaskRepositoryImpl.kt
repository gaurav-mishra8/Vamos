package com.greenbot.vamos.data

import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.repository.TaskRepository
import javax.inject.Inject
import javax.inject.Named

class TaskRepositoryImpl @Inject constructor(
    @Named("local") private val localDataStore: TaskDataStore,
    @Named("remote") private val remoteDataStore: TaskDataStore
) : TaskRepository {

    override fun getAllTasks(): List<Task> {
        return localDataStore.getAllTasks()
    }

    override fun saveTask(task: Task): Boolean {
        return localDataStore.saveTask(task)
    }

    override fun getTaskById(taskId: Long): Task? {
        return localDataStore.getTask(taskId)
    }

    override fun deleteTask(taskId: Long): Boolean {
        return localDataStore.deleteTask(taskId)
    }
}