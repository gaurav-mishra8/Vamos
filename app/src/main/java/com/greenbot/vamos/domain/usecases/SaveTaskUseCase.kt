package com.greenbot.vamos.domain.usecases

import com.greenbot.vamos.di.IoDispatcher
import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.repository.TaskRepository
import com.greenbot.vamos.domain.usecases.base.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveTaskUseCase @Inject constructor(
    private val repository: TaskRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<SaveTaskUseCase.Params, Boolean>(ioDispatcher) {

    override suspend fun execute(parameters: Params): Boolean {
        return repository.saveTask(parameters.task)
    }

    data class Params(val task: Task)
}