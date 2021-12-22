package com.greenbot.vamos.domain.usecases

import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.repository.TaskRepository
import javax.inject.Inject

class SaveTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) : UseCase<SaveTaskUseCase.Params, Boolean> {

    override fun execute(params: Params): Boolean {
        return repository.saveTask(params.task)
    }

    data class Params(val task: Task)
}