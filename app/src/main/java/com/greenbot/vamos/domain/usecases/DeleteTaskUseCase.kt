package com.greenbot.vamos.domain.usecases

import com.greenbot.vamos.di.IoDispatcher
import com.greenbot.vamos.domain.repository.TaskRepository
import com.greenbot.vamos.domain.result.Result
import com.greenbot.vamos.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<DeleteTaskUseCase.Params, Boolean>(dispatcher) {

    override fun execute(parameters: Params): Flow<Result<Boolean>> {
        return repository.deleteTask(parameters.taskId)
    }

    data class Params(
        val taskId: Long
    )
}