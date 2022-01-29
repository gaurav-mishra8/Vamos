package com.greenbot.vamos.domain.usecases

import com.greenbot.vamos.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) : UseCase<DeleteTaskUseCase.Params, Boolean> {

    override fun execute(params: Params): Boolean {
        return repository.deleteTask(params.taskId)
    }

    data class Params(
        val taskId: Long
    )
}