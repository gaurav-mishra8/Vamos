package com.greenbot.vamos.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greenbot.vamos.data.local.db.model.TaskEntity

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: TaskEntity)

    @Query("select * from tasks")
    fun getAllTasks(): List<TaskEntity>

    @Query("SELECT * from tasks WHERE id = :taskId")
    fun getTaskById(taskId: Long): TaskEntity?

    @Query("DELETE from tasks WHERE id = :taskId")
    fun deleteTask(taskId: Long)
}