package com.greenbot.vamos.data.local

import com.greenbot.vamos.data.TaskDataStore
import com.greenbot.vamos.data.local.db.TaskDao
import com.greenbot.vamos.domain.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class LocalTaskDataSource(
    private val taskDao: TaskDao
) : TaskDataStore {

    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    override fun saveTask(task: Task): Boolean {
        return taskDao.insert(task)
    }

    override fun getTask(taskId: Long): Task {
        return taskDao.getTaskById(taskId = taskId)
    }

    override fun deleteTask(taskId: Long): Boolean {
        TODO("Not yet implemented")
    }
}