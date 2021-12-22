package com.greenbot.vamos.domain.usecases

import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val repo: TaskRepository
) : UseCase<Nothing, Flow<List<Task>>> {

    override fun execute(params: Nothing): Flow<List<Task>> {
        return repo.getAllTasks()
    }
}