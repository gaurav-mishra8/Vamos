package com.greenbot.vamos.ui.taskList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.greenbot.vamos.MainCoroutineRule
import com.greenbot.vamos.domain.result.Result
import com.greenbot.vamos.domain.usecases.GetAllTasksUseCase
import com.greenbot.vamos.taskList
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class TaskListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var taskListViewModel: TaskListViewModel

    private val getAllTaskUseCase = mock(GetAllTasksUseCase::class.java)

    @Before
    fun setUp() {
        val savedStateHandle = mock(SavedStateHandle::class.java)
        taskListViewModel = TaskListViewModel(savedStateHandle, getAllTaskUseCase)

        `when`(getAllTaskUseCase.invoke(any())).thenAnswer { flowOf(Result.Success(taskList)) }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_tasks_are_loaded_successfully() {

        mainCoroutineRule.testDispatcher.runBlockingTest {
            val states = mutableListOf<TaskListState>()
            val stateJob = launch {
                taskListViewModel.viewState.toList(states)
            }

            taskListViewModel.processIntent(TaskListIntent.LoadInitialTasks)
            stateJob.cancelAndJoin()

            assert(states.last().taskList == taskList)

        }
    }
}