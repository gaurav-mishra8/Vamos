package com.greenbot.vamos.domain.usecases

import com.greenbot.vamos.di.IoDispatcher
import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.repository.TaskRepository
import com.greenbot.vamos.domain.usecases.base.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val repo: TaskRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, List<Task>>(ioDispatcher) {

    override suspend fun execute(parameters: Unit): List<Task> {
        return repo.getAllTasks()
    }
}