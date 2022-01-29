package com.greenbot.vamos.ui.taskList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.greenbot.vamos.domain.result.Result
import com.greenbot.vamos.domain.usecases.GetAllTasksUseCase
import com.greenbot.vamos.mvi.AbstractViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TaskListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllTasksUseCase: GetAllTasksUseCase
) : AbstractViewModel<TaskListIntent, TaskListState, TaskListEvent>() {

    override val viewState: StateFlow<TaskListState>

    init {
        val initialVS = TaskListState.initial()

        viewState = merge(
            intentFlow.filterIsInstance<TaskListIntent.LoadInitialTasks>().take(1),
            intentFlow.filterNot { it is TaskListIntent.LoadInitialTasks }
        )
            .toPartialChangeFlow()
            .sendSingleEvent()
            .scan(initialVS) { vs, change -> change.reduce(vs) }
            .catch { Timber.tag("VM").e(it, "[MAIN_VM] Throwable: $it") }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                initialVS
            )
    }

    private fun Flow<TaskListIntent>.toPartialChangeFlow(): Flow<PartialChange> {
        return shareIn(viewModelScope, SharingStarted.WhileSubscribed()).run {
            val getTasks = getAllTasksUseCase(Unit)
                .map { result ->
                    when (result) {
                        is Result.Loading -> PartialChange.GetTasks.Loading
                        is Result.Error -> PartialChange.GetTasks.Error(
                            result.exception.localizedMessage ?: "error"
                        )
                        is Result.Success -> PartialChange.GetTasks.Data(result.data)
                    }
                }

            val refreshTasks = getAllTasksUseCase(Unit)
                .map { result ->
                    when (result) {
                        is Result.Loading -> PartialChange.GetTasks.Loading
                        is Result.Error -> PartialChange.GetTasks.Error(
                            result.exception.localizedMessage ?: "error"
                        )
                        is Result.Success -> PartialChange.GetTasks.Data(result.data)
                    }
                }

            return@run merge(
                filterIsInstance<TaskListIntent.LoadInitialTasks>().flatMapLatest { getTasks },
                filterIsInstance<TaskListIntent.RefreshTasks>().flatMapLatest { refreshTasks }
            )
        }
    }

    private fun Flow<PartialChange>.sendSingleEvent(): Flow<PartialChange> {
        return onEach {

        }
    }
}