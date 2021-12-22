package com.greenbot.vamos.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.greenbot.vamos.data.local.db.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}