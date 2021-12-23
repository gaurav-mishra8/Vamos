package com.greenbot.vamos.data.di

import android.app.Application
import androidx.room.Room
import com.greenbot.vamos.data.TaskDataStore
import com.greenbot.vamos.data.TaskRepositoryImpl
import com.greenbot.vamos.data.local.LocalTaskDataSource
import com.greenbot.vamos.data.local.db.TaskDao
import com.greenbot.vamos.data.local.db.TaskDatabase
import com.greenbot.vamos.data.mapper.TaskEntityMapper
import com.greenbot.vamos.data.remote.RemoteTaskDataSource
import com.greenbot.vamos.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Application): TaskDatabase {
        return Room.databaseBuilder(context, TaskDatabase::class.java, "taskDb").build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(db: TaskDatabase): TaskDao {
        return db.taskDao()
    }

    @Singleton
    @Provides
    fun provideTaskRepository(
        @Named("local") localDataStore: TaskDataStore,
        @Named("remote") remoteDataStore: TaskDataStore
    ): TaskRepository {
        return TaskRepositoryImpl(localDataStore, remoteDataStore)
    }

    @Singleton
    @Provides
    @Named("local")
    fun provideLocalDataStore(taskDao: TaskDao, taskEntityMapper: TaskEntityMapper): TaskDataStore {
        return LocalTaskDataSource(taskDao, taskEntityMapper)
    }

    @Singleton
    @Provides
    @Named("remote")
    fun provideRemoteDataStore(): TaskDataStore {
        return RemoteTaskDataSource()
    }
}