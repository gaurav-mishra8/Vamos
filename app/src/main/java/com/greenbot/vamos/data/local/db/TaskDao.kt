package com.greenbot.vamos.data.local.db

import androidx.room.*
import com.greenbot.vamos.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task): Boolean

    @Query("select * from tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * from tasks WHERE id = :taskId")
    fun getTaskById(taskId: Long): Task

    @Query("DELETE from tasks WHERE id = :taskId")
    fun deleteTask(taskId: Long)
}