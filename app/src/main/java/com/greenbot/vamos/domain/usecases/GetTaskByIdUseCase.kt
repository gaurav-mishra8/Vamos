package com.greenbot.vamos.domain.usecases

import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val repository: TaskRepository
) : UseCase<GetTaskByIdUseCase.Params, Task> {

    override fun execute(params: Params): Task {
        return repository.getTask(params.taskId)
    }

    data class Params(
        val taskId: Long
    )
}