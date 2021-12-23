package com.greenbot.vamos.domain.usecases

import com.greenbot.vamos.di.IoDispatcher
import com.greenbot.vamos.domain.repository.TaskRepository
import com.greenbot.vamos.domain.usecases.base.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<DeleteTaskUseCase.Params, Boolean>(dispatcher) {


    override suspend fun execute(parameters: Params): Boolean {
        return repository.deleteTask(parameters.taskId)
    }

    data class Params(
        val taskId: Long
    )
}