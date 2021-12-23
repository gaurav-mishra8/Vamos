package com.greenbot.vamos.data.mapper

import com.greenbot.vamos.data.local.db.model.TaskEntity
import com.greenbot.vamos.domain.Mapper
import com.greenbot.vamos.domain.model.Task
import javax.inject.Inject

class TaskEntityMapper @Inject constructor() : Mapper<TaskEntity, Task> {

    override fun mapFromDomain(type: Task): TaskEntity {
        return TaskEntity(
            id = type.id,
            title = type.title,
            description = type.description,
            isCompleted = type.isCompleted,
            priority = type.priority,
            createdTime = type.createdTime,
            updatedTime = type.updatedTime,
            targetCompletionTime = type.targetCompletionTime
        )
    }

    override fun mapToDomain(type: TaskEntity): Task {
        return Task(
            id = type.id,
            title = type.title,
            description = type.description,
            isCompleted = type.isCompleted,
            priority = type.priority,
            createdTime = type.createdTime,
            updatedTime = type.updatedTime,
            targetCompletionTime = type.targetCompletionTime
        )
    }
}