package com.greenbot.vamos.ui.taskList

import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.mvi.MviIntent
import com.greenbot.vamos.mvi.MviSingleEvent
import com.greenbot.vamos.mvi.MviViewState

sealed class TaskListIntent : MviIntent {
    object LoadInitialTasks : TaskListIntent()
    object RefreshTasks : TaskListIntent()
    data class OpenTaskDetail(val taskId: Long) : TaskListIntent()
}

data class TaskListState(
    val isLoading: Boolean,
    val taskList: List<Task>,
    val isError: Boolean,
    val errorMessage: String?,
) : MviViewState {

    companion object {
        fun initial(): TaskListState {
            return TaskListState(
                isLoading = false,
                taskList = emptyList(),
                isError = false,
                errorMessage = null
            )
        }
    }
}

sealed interface TaskListEvent : MviSingleEvent {
    object Retry : TaskListEvent
    object Refresh : TaskListEvent
}

internal sealed interface PartialChange {
    fun reduce(vs: TaskListState): TaskListState

    sealed class GetTasks : PartialChange {
        override fun reduce(vs: TaskListState): TaskListState {
            return when (this) {
                Loading -> vs.copy(
                    isLoading = true,
                    errorMessage = null,
                    isError = false
                )
                is Data -> vs.copy(
                    isLoading = false,
                    errorMessage = null,
                    isError = false,
                    taskList = tasks
                )
                is Error -> vs.copy(
                    isLoading = false,
                    errorMessage = error,
                    isError = true
                )
            }
        }

        object Loading : GetTasks()
        data class Data(val tasks: List<Task>) : GetTasks()
        data class Error(val error: String) : GetTasks()
    }

    sealed class Refresh : PartialChange {
        override fun reduce(vs: TaskListState): TaskListState {
            return when (this) {
                is Success -> vs.copy(isLoading = false)
                is Failure -> vs.copy(isLoading = false)
                Loading -> vs.copy(isLoading = true)
            }
        }

        object Loading : Refresh()
        object Success : Refresh()
        data class Failure(val error: String) : Refresh()
    }
}

