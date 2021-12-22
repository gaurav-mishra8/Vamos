package com.greenbot.vamos.data.local

import com.greenbot.vamos.data.local.db.TaskDao
import com.greenbot.vamos.domain.model.Task
import javax.inject.Inject

class TaskCacheImpl @Inject constructor(
    private val taskdb: TaskDao
) : TaskCache {

    override fun getAllTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override fun getTaskById(taskId: Long): Task {
        TODO("Not yet implemented")
    }

    override fun saveTask(task: Task): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteTask(taskId: Long): Boolean {
        TODO("Not yet implemented")
    }
}