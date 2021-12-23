package com.greenbot.vamos.data

import com.greenbot.vamos.domain.model.Task

interface TaskDataStore {
    fun getAllTasks(): List<Task>
    fun saveTask(task: Task): Boolean
    fun getTask(taskId: Long): Task?
    fun deleteTask(taskId: Long): Boolean
}