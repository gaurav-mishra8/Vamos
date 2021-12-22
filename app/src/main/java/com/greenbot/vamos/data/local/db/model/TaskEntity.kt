package com.greenbot.vamos.data.local.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "isCompleted")
    val isCompleted: Boolean,
    @ColumnInfo(name = "priority")
    val priority: Int,
    @ColumnInfo(name = "createdTime")
    val createdTime: Long,
    @ColumnInfo(name = "updatedTime")
    val updatedTime: Long,
    @ColumnInfo(name = "targetCompletionTime")
    val targetCompletionTime: Long,
)