package com.greenbot.vamos.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.greenbot.vamos.FakeTaskRepository
import com.greenbot.vamos.MainCoroutineRule
import com.greenbot.vamos.domain.result.Result
import com.greenbot.vamos.runBlockingTest
import com.greenbot.vamos.taskList
import kotlinx.coroutines.flow.toList
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetAllTasksUseCaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var usecase: GetAllTasksUseCase

    @Before
    fun setUp() {
        val repository = FakeTaskRepository()
        usecase = GetAllTasksUseCase(repository, mainCoroutineRule.testDispatcher)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun verify_all_tasks_are_returned_by_usecase() {
        mainCoroutineRule.runBlockingTest {
            val useCaseResult = usecase(Unit).toList()

            assert(useCaseResult.first() is Result.Loading)
            assert((useCaseResult.last() as Result.Success).data == taskList)
        }
    }
}