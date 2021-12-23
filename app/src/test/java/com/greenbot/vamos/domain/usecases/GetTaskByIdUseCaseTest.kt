package com.greenbot.vamos.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.greenbot.vamos.FakeTaskRepository
import com.greenbot.vamos.MainCoroutineRule
import com.greenbot.vamos.domain.result.Result
import com.greenbot.vamos.domain.result.data
import com.greenbot.vamos.runBlockingTest
import com.greenbot.vamos.task1
import kotlinx.coroutines.flow.toList
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetTaskByIdUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private var fakeTaskRepo = FakeTaskRepository()

    private lateinit var usecase: GetTaskByIdUseCase

    @Before
    fun setUp() {
        usecase = GetTaskByIdUseCase(fakeTaskRepo, coroutineRule.testDispatcher)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun verify_correct_task_is_returned() {

        coroutineRule.runBlockingTest {
            val useCaseResult = usecase(GetTaskByIdUseCase.Params(taskId = task1.id))
                .toList()

            assert(useCaseResult.first() is Result.Loading)
            assertThat(task1, `is`(equalTo(useCaseResult.last().data)))
        }
    }

    @Test
    fun verify_task_not_found_error_returned_when_task_not_present() {
        coroutineRule.runBlockingTest {
            val useCaseResult = usecase(GetTaskByIdUseCase.Params(taskId = 342323L))
                .toList()

            assert(useCaseResult.first() is Result.Loading)
            assert((useCaseResult.last() as Result.Error).exception is TaskNotFoundException)
        }
    }
}