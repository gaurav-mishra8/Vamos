package com.greenbot.vamos.domain.usecases

import com.greenbot.vamos.di.IoDispatcher
import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.repository.TaskRepository
import com.greenbot.vamos.domain.result.Result
import com.greenbot.vamos.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val repository: TaskRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<GetTaskByIdUseCase.Params, Task>(ioDispatcher) {

    override fun execute(parameters: Params): Flow<Result<Task>> {
        return repository.getTaskById(parameters.taskId).map {
            when (it) {
                is Result.Success -> {
                    if (it.data != null) {
                        Result.Success(it.data)
                    } else {
                        Result.Error(TaskNotFoundException())
                    }
                }
                is Result.Error -> Result.Error(it.exception)
                is Result.Loading -> Result.Loading
            }
        }
    }

    data class Params(
        val taskId: Long
    )
}

class TaskNotFoundException : Exception("Task not found")