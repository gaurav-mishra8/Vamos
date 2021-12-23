package com.greenbot.vamos.data.remote

import com.greenbot.vamos.data.TaskDataStore
import com.greenbot.vamos.domain.model.Task

class RemoteTaskDataSource : TaskDataStore {

    override fun getAllTasks(): List<Task> {
        throw UnsupportedOperationException()
    }

    override fun saveTask(task: Task): Boolean {
        throw UnsupportedOperationException()
    }

    override fun getTask(taskId: Long): Task {
        throw UnsupportedOperationException()
    }

    override fun deleteTask(taskId: Long): Boolean {
        throw UnsupportedOperationException()
    }
}