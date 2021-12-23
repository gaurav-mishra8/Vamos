package com.greenbot.vamos.domain.usecases

import com.greenbot.vamos.di.IoDispatcher
import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.repository.TaskRepository
import com.greenbot.vamos.domain.usecases.base.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val repository: TaskRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<GetTaskByIdUseCase.Params, Task?>(ioDispatcher) {

    override suspend fun execute(parameters: Params): Task? {
        return repository.getTaskById(parameters.taskId)
    }

    data class Params(
        val taskId: Long
    )
}