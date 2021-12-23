package com.greenbot.vamos.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.greenbot.vamos.data.local.db.TaskDao
import com.greenbot.vamos.data.local.db.TaskDatabase
import com.greenbot.vamos.testTaskEntity1
import com.greenbot.vamos.testTaskEntity2
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TaskDatabaseTest {

    private lateinit var taskDao: TaskDao
    private lateinit var taskDatabase: TaskDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        taskDatabase = Room.inMemoryDatabaseBuilder(context, TaskDatabase::class.java).build()
        taskDao = taskDatabase.taskDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        taskDatabase.close()
    }

    @Test
    fun verify_task_insertion_is_success() {
        taskDao.insert(testTaskEntity1)
        taskDao.insert(testTaskEntity2)

        val taskList = taskDao.getAllTasks()
        assert(taskList.size == 2)
    }

    @Test
    fun verify_task_deletion_is_success() {
        taskDao.insert(testTaskEntity1)
        taskDao.insert(testTaskEntity2)

        var taskList = taskDao.getAllTasks()
        assert(taskList.size == 2)

        taskDao.deleteTask(testTaskEntity1.id)
        taskList = taskDao.getAllTasks()
        assert(taskList.size == 1)

        val result = taskDao.getTaskById(testTaskEntity1.id)
        assert(result == null)
    }

    @Test
    fun verify_getTaskById_returns_null_if_task_not_found() {
        val task = taskDao.getTaskById(123L)
        assert(task == null)
    }

    @Test
    fun verify_getTaskById_returns_task_if_found() {
        taskDao.insert(testTaskEntity1)
        taskDao.insert(testTaskEntity2)

        val task = taskDao.getTaskById(testTaskEntity2.id)
        assert(task == testTaskEntity2)
    }
}