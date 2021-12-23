package com.greenbot.vamos.domain.usecases

import com.greenbot.vamos.di.IoDispatcher
import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.repository.TaskRepository
import com.greenbot.vamos.domain.result.Result
import com.greenbot.vamos.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveTaskUseCase @Inject constructor(
    private val repository: TaskRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<SaveTaskUseCase.Params, Boolean>(ioDispatcher) {

    override fun execute(parameters: Params): Flow<Result<Boolean>> {
        return repository.saveTask(parameters.task)
    }

    data class Params(val task: Task)
}