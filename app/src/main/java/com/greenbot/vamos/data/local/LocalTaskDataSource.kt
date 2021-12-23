package com.greenbot.vamos.data.local

import com.greenbot.vamos.data.TaskDataStore
import com.greenbot.vamos.data.local.db.TaskDao
import com.greenbot.vamos.data.mapper.TaskEntityMapper
import com.greenbot.vamos.domain.model.Task

class LocalTaskDataSource(
    private val taskDao: TaskDao,
    private val entityMapper: TaskEntityMapper
) : TaskDataStore {

    override fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks().map {
            entityMapper.mapToDomain(it)
        }
    }

    override fun saveTask(task: Task): Boolean {
        taskDao.insert(entityMapper.mapFromDomain(task))
        return true
    }

    override fun getTask(taskId: Long): Task? {
        val entity = taskDao.getTaskById(taskId)
        return if (entity != null)
            entityMapper.mapToDomain(entity)
        else
            null
    }

    override fun deleteTask(taskId: Long): Boolean {
        taskDao.deleteTask(taskId)
        return true
    }
}