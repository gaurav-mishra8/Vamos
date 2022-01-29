package com.greenbot.vamos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.greenbot.vamos.domain.model.Task
import com.greenbot.vamos.domain.repository.TaskRepository
import com.greenbot.vamos.domain.result.Result
import com.greenbot.vamos.domain.usecases.GetAllTasksUseCase
import com.greenbot.vamos.ui.taskList.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colors.background) {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MainScreen(
    vm: TaskListViewModel = viewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val singleEvent = rememberFlowWithLifecycle(vm.singleEvent)
    val state by vm.viewState.collectAsState()
    val intentChannel = remember { Channel<TaskListIntent>(Channel.UNLIMITED) }

    LaunchedEffect(vm) {
        intentChannel
            .consumeAsFlow()
            .onStart { emit(TaskListIntent.LoadInitialTasks) }
            .onEach(vm::processIntent)
            .collect()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("VAMOS") }
            )
        },
        scaffoldState = scaffoldState,
    ) {
        MainContent(
            state = state,
            onRefresh = { intentChannel.trySend(TaskListIntent.RefreshTasks) },
        )
    }
}

@Composable
fun MainContent(
    state: TaskListState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {

    if (state.isError) {
        return ErrorView(modifier, state.errorMessage)
    }

    if (state.isLoading) {
        return LoadingIndicator(modifier)
    }

    if (state.taskList.isEmpty()) {
        return EmptyView(modifier, "No Tasks Added")
    }

    TaskList(modifier, state.taskList)
}

@Preview
@Composable
fun PreviewMainScreen() {
    val repo = object : TaskRepository {
        override fun getAllTasks(): Flow<Result<List<Task>>> {
            return flowOf(
                Result.Success(
                    listOf(
                        Task(
                            id = 1223456L,
                            title = "Test Task 1",
                            description = "Test Task Description 1",
                            createdTime = System.currentTimeMillis(),
                            updatedTime = System.currentTimeMillis(),
                            targetCompletionTime = 1640320810000L
                        ),
                        Task(
                            id = 1223456L,
                            title = "Test Task 1",
                            description = "Test Task Description 1",
                            createdTime = System.currentTimeMillis(),
                            updatedTime = System.currentTimeMillis(),
                            targetCompletionTime = 1640320810000L
                        )
                    )
                )
            )
        }

        override fun saveTask(task: Task): Flow<Result<Boolean>> {
            TODO("Not yet implemented")
        }

        override fun getTaskById(taskId: Long): Flow<Result<Task?>> {
            TODO("Not yet implemented")
        }

        override fun deleteTask(taskId: Long): Flow<Result<Boolean>> {
            TODO("Not yet implemented")
        }
    }

    MainScreen(
        vm = TaskListViewModel(
            savedStateHandle = SavedStateHandle(),
            getAllTasksUseCase = GetAllTasksUseCase(repo, Dispatchers.Main)
        )
    )
}